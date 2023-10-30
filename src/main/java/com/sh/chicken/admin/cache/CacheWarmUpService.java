package com.sh.chicken.admin.cache;


import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeQueryRepositoryImpl;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepositoryCustom;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import com.sh.chicken.global.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.sh.chicken.global.common.RedisConst.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CacheWarmUpService {

    private final ChickenMenuRepositoryCustom chickenMenuRepositoryCustom;
    private final ChickenMenuRepository chickenMenuRepository;
    private final ChickenLikeQueryRepositoryImpl chickenLikeRepositoryCustom;
    private final ChickenLikeRepository chickenLikeRepository;
    private final UsersRepository usersRepository;
    private final RedisUtil redisUtil;


    /**
     * 전체 menu List bulk insert
     */
    public void pushAllChickenMenusBulkInsert(){
        //redis에서 가져와서 db에 저장

        //redis에서 삭제

        //가져오기
        List<ChickenMenuInfoResDto> allMenusWithTotalLikePriceDesc = chickenMenuRepositoryCustom.getAllMenusWithTotalLikePriceDesc();

        //redis에 넣기
        redisUtil.bulkInsertForMenuList(MAIN_BY_PRICE.prefix(), allMenusWithTotalLikePriceDesc);
        log.info("======== push menu list to redis bulk insert========");

    }

    /**
     * 개별 menu info
     */
    public void pushChickenMenuInfo(){
        List<ChickenMenuInfoResDto> allMenus = chickenMenuRepositoryCustom.getAllMenus();

        redisUtil.putString(MENU.prefix(), allMenus, null);
        for (Long i = 1L; i <= allMenus.size(); i++) {
            redisUtil.putString(MENU.prefix() + i, allMenus.get(Long.valueOf(i-1).intValue()), null);
        }
        log.info("======= push menu info to redis =======");

    }


    /**
     * 각 치킨 별 좋아요한 사람, bulk insert
     */
    public void pushChickenMenuLikeBulkInsert(){
        Long totalMenuCount = getTotalMenuNum();

        for (Long i = 1L; i <= totalMenuCount; i++) {
            List<Long> userIdList = chickenLikeRepositoryCustom.getLikesByMenuId(i);
            redisUtil.bulkInsertForLikes(LIKE.prefix() + i, userIdList);

        }

        log.info("======= push menu likes to redis bulk insert =======");

    }

    /**
     * redis - db 정합성 맞추기
     */
    public void matchConsistency(){
        Long totalMenuCount = getTotalMenuNum();

        // cache - db 정합성
        for (Long menuId = 1L; menuId <= totalMenuCount; menuId++) {
            Set<Long> fromRedis = redisUtil.getSetMembers(LIKE.prefix() + menuId); // redis에서 가져오기
            List<Long> userIdList = chickenLikeRepositoryCustom.getLikesByMenuId(menuId); // db에서 가져오기
            Set<Long> deleteEle = new HashSet<>(); // db, bulk delete
            List<ChickenLike> saveEle = new ArrayList<>(); // db bulk insert
            ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(menuId).get();


            for (Long userId : userIdList) {
                if(fromRedis.contains(userId)) { // 만약 db에 있는 값이 redis에 있다면 -> set에서 삭제 : 유지(좋아요 유지)
                    fromRedis.remove(userId);
                }
                else { // 만약 db에 있는 값는 redis에 없다면 -> db에서 삭제 : 삭제(좋아요 삭제)
                    deleteEle.add(userId);
                }

            }
            chickenLikeRepositoryCustom.deleteLikeByUserId(menuId, deleteEle); // 한번에 db에서 delete

            // fromRedis 남은 값들은 새로 좋아요한 사람들
            for(Long userId : fromRedis){
                Users users = usersRepository.findById(userId).get();

                saveEle.add(ChickenLike.createChickenLike(users, chickenMenu)); // 추가
            }
            chickenLikeRepository.saveAll(saveEle); // 한번에 db에 insert
        }


    }

    private void setTotalLikeNum(Long menuId, ChickenMenuInfoResDto chickenMenuInfoResDto) {
        Long likeTotalSize = redisUtil.getLikeTotalSize(LIKE.prefix() + menuId);
        chickenMenuInfoResDto.setLikes(likeTotalSize);
    }

    private Long getTotalMenuNum() {
        Long totalMenuCount = chickenMenuRepositoryCustom.getTotalMenuCount();
        return totalMenuCount;
    }



}
