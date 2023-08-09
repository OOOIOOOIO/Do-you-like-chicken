package com.sh.chicken.admin.application;


import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepositoryCustom;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepositoryCustom;
import com.sh.chicken.global.common.RedisConst;
import com.sh.chicken.global.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static com.sh.chicken.global.common.RedisConst.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CacheWarmUpService {

    private final ChickenMenuRepositoryCustom chickenMenuRepositoryCustom;
    private final ChickenLikeRepositoryCustom chickenLikeRepositoryCustom;
    private final RedisUtil redisUtil;

    /**
     * warm up 항목
     * - 전체 menu list
     * - 개별 menu info
     * - 각 치킨 별 좋아요한 사람
     */

    /**
     * 전체 menu List(Main에서 사용)
     */
    public void pushAllChickenMenus(){
        //redis에서 가져와서 db에 저장

        //redis에서 삭제

        //가져오기
        List<ChickenMenuInfoResDto> allMenusWithTotalLikePriceDesc = chickenMenuRepositoryCustom.getAllMenusWithTotalLikePriceDesc();

        //redis에 넣기
        redisUtil.putString(MAIN.prefix(), allMenusWithTotalLikePriceDesc, null);
        log.info("======== push menu list to redis ========");

    }

    /**
     * 개별 menu info
     */
    public void pushChickenMenuInfo(){
        List<ChickenMenuInfoResDto> allMenus = chickenMenuRepositoryCustom.getAllMenus();

        for (Long i = 1L; i <= allMenus.size(); i++) {
            redisUtil.putString(MENU.prefix() + i, allMenus.get(Long.valueOf(i-1).intValue()), null);
        }
        log.info("======= push menu info to redis =======");

    }


    /**
     * 각 치킨 별 좋아요한 사람
     */
    public void pushChickenMenuLike(){
        Long totalMenuCount = getTotalMenuNum();

        for (Long i = 1L; i <= totalMenuCount; i++) {
            List<Long> likesByMenuId = chickenLikeRepositoryCustom.getLikesByMenuId(i);
            for (Long userId : likesByMenuId) {
                redisUtil.putSet(LIKE.prefix() + i, userId, null);

            }

        }

        log.info("======= push menu likes to redis =======");

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
