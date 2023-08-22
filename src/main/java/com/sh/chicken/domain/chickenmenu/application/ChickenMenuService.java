package com.sh.chicken.domain.chickenmenu.application;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResListDto;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepositoryCustom;
import com.sh.chicken.global.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.sh.chicken.global.common.RedisConst.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChickenMenuService {

    private final ChickenMenuRepositoryCustom chickenMenuRepositoryCustom;
    private final RedisUtil redisUtil;

    /**
     * main, sort 가격순으로 정렬
     */
    public ChickenMenuInfoResListDto getAllChickenMenus() {
        if(redisUtil.isExists(MAIN.prefix())){
            List<ChickenMenuInfoResDto> getAllChickenMenuWithLikeFromRedis = redisUtil.getByClassType(MAIN.prefix(), List.class);

            for (ChickenMenuInfoResDto chickenMenuInfoResDto : getAllChickenMenuWithLikeFromRedis) { // redis에서 좋아요 총 개수
                setTotalLikeNum(chickenMenuInfoResDto.getMenuId(), chickenMenuInfoResDto);

            }

            Collections.sort(getAllChickenMenuWithLikeFromRedis, (o1, o2) -> (o2.getPrice() - o1.getPrice())); // 정렬(내림차순)

            log.info("==== FROM REDIS ====");
            return new ChickenMenuInfoResListDto(getAllChickenMenuWithLikeFromRedis);
        }
        else {
            List<ChickenMenuInfoResDto> allMenusWithTotalLike = chickenMenuRepositoryCustom.getAllMenusWithTotalLikePriceDesc(); // 일단 db에서 가져옴

            redisUtil.putString(MAIN.prefix(), allMenusWithTotalLike, null);

            log.info("==== FROM DB TO REDIS ====");
            return new ChickenMenuInfoResListDto(allMenusWithTotalLike);
        }
    }

    /**
     * like순 정렬 main
     * --> 이거 캐시+ sorting이랑 db랑 한번 해보자
     */
    public ChickenMenuInfoResListDto getChickenMenusOrderByLikesDesc(){
//        List<ChickenMenuInfoResDto> allMenusWithTotalLike = chickenMenuRepositoryCustom.getAllMenusWithTotalLikeLikesDesc();

        List<ChickenMenuInfoResDto> allMenusWithTotalLike = redisUtil.getByClassType(MAIN.prefix(), List.class);

        for (ChickenMenuInfoResDto chickenMenuInfoResDto : allMenusWithTotalLike) { // redis에서 좋아요 총 개수
            setTotalLikeNum(chickenMenuInfoResDto.getMenuId(), chickenMenuInfoResDto);

        }
        Collections.sort(allMenusWithTotalLike, (o1, o2) -> (int)(o2.getLikes() - o1.getLikes())); // 정렬(내림차순)

        return new ChickenMenuInfoResListDto(allMenusWithTotalLike);
    }

    /**
     * menu 상세
     */
    public ChickenMenuInfoResDto getMenuInfo(Long menuId) {
        if (redisUtil.isExists(MENU.prefix() + menuId)) {
            ChickenMenuInfoResDto chickenMenuFromRedis = redisUtil.getByClassType(MENU.prefix() + menuId, ChickenMenuInfoResDto.class);
            setTotalLikeNum(menuId, chickenMenuFromRedis); // redis에서 총 개수

            log.info("==== FROM REDIS ====");

            return chickenMenuFromRedis;
        } else {
            ChickenMenuInfoResDto chickenMenuInfoResDto = chickenMenuRepositoryCustom.getMenuInfo(menuId).get();
            redisUtil.putString(MENU.prefix() + menuId, chickenMenuInfoResDto, null);

            log.info("==== FROM DB TO REDIS ====");

            return chickenMenuInfoResDto;
        }

    }

    private void setTotalLikeNum(Long menuId, ChickenMenuInfoResDto chickenMenuInfoResDto) {
        Long likeTotalSize = redisUtil.getLikeTotalSize(LIKE.prefix() + menuId);
        chickenMenuInfoResDto.setLikes(likeTotalSize);
    }



    // =======================================================================

    public void getAllMenusFromRedis(){
        //main redis에서 가져오고
        //for문 돌면서 like total 가져오기
    }

    public void getAllMenusFromRedisOrderByDesc(){
        //main
        //for문 돌고 likes 기준으로 메모리에서 sorting
    }

    public void menuInfoFromRedis(){

    }


}
