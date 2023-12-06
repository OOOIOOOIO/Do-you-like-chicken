package com.sh.chicken.domain.chickenmenu.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResListDto;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuQueryRepositoryImpl;
import com.sh.chicken.global.exception.CustomException;
import com.sh.chicken.global.exception.CustomErrorCode;
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

    private final ChickenMenuQueryRepositoryImpl chickenMenuQueryRepositoryImpl;
    private final RedisUtil redisUtil;
    private final ObjectMapper objectMapper;

    /**
     * 가격순으로 정렬
     */
    public ChickenMenuInfoResListDto getAllChickenMenus() {
        if(redisUtil.isExists(MAIN_BY_PRICE.prefix())){
            List<ChickenMenuInfoResDto> getAllChickenMenuWithLikeFromRedis = redisUtil.getByClassType(MAIN_BY_PRICE.prefix(), List.class);

            List<ChickenMenuInfoResDto> chickenMenuInfoResDtos = getChickenMenuInfoResDtoList(getAllChickenMenuWithLikeFromRedis);

            for (ChickenMenuInfoResDto chickenMenuInfoResDto : chickenMenuInfoResDtos) { // redis에서 좋아요 총 개수
                setTotalLikeNum(chickenMenuInfoResDto.getMenuId(), chickenMenuInfoResDto);

            }

            Collections.sort(chickenMenuInfoResDtos, (o1, o2) -> (o2.getPrice() - o1.getPrice())); // 정렬(내림차순)

            log.info("==== FROM REDIS ====");
            return new ChickenMenuInfoResListDto(chickenMenuInfoResDtos);
        }
        else {
            List<ChickenMenuInfoResDto> allMenusWithTotalLike = chickenMenuQueryRepositoryImpl.getAllMenusWithTotalLikePriceDesc(); // 일단 db에서 가져옴

            if(allMenusWithTotalLike.isEmpty()) throw new CustomException(CustomErrorCode.NotFoundChickenMenuListException);

            redisUtil.putString(MAIN_BY_PRICE.prefix(), allMenusWithTotalLike, null);

            log.info("==== FROM DB TO REDIS ====");
            return new ChickenMenuInfoResListDto(allMenusWithTotalLike);
        }
    }

    /**
     * LinkedHashMap cannot be cast to object Error 해결
     *
     * @param getAllChickenMenuWithLikeFromRedis
     * @return
     */
    private List<ChickenMenuInfoResDto> getChickenMenuInfoResDtoList(List<ChickenMenuInfoResDto> getAllChickenMenuWithLikeFromRedis) {
        List<ChickenMenuInfoResDto> chickenMenuInfoResDtos = objectMapper.convertValue(getAllChickenMenuWithLikeFromRedis, new TypeReference<List<ChickenMenuInfoResDto>>() {});

        return chickenMenuInfoResDtos;
    }

    /**
     * like순 정렬 main
     * --> 이거 캐시+ sorting이랑 db랑 한번 해보자
     */
    public ChickenMenuInfoResListDto getChickenMenusOrderByLikesDesc(){

        if(redisUtil.isExists(MAIN_BY_LIKE.prefix())){
            List<ChickenMenuInfoResDto> allMenusWithTotalLike = redisUtil.getByClassType(MAIN_BY_LIKE.prefix(), List.class);

            List<ChickenMenuInfoResDto> chickenMenuInfoResDtos = getChickenMenuInfoResDtoList(allMenusWithTotalLike);

            for (ChickenMenuInfoResDto chickenMenuInfoResDto : chickenMenuInfoResDtos) { // redis에서 좋아요 총 개수
                setTotalLikeNum(chickenMenuInfoResDto.getMenuId(), chickenMenuInfoResDto);

            }
            Collections.sort(chickenMenuInfoResDtos, (o1, o2) -> (int)(o2.getLikes() - o1.getLikes())); // 정렬(내림차순)

            log.info("==== FROM REDIS ====");
            return new ChickenMenuInfoResListDto(chickenMenuInfoResDtos);
        }
        else {
            List<ChickenMenuInfoResDto> allMenusWithTotalLike = chickenMenuQueryRepositoryImpl.getAllMenusWithTotalLikePriceDesc(); // 일단 db에서 가져옴

            if(allMenusWithTotalLike.isEmpty()) throw new CustomException(CustomErrorCode.NotFoundChickenMenuListException);

            redisUtil.putString(MAIN_BY_LIKE.prefix(), allMenusWithTotalLike, null);

            log.info("==== FROM DB TO REDIS ====");
            return new ChickenMenuInfoResListDto(allMenusWithTotalLike);
        }
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
            ChickenMenuInfoResDto chickenMenuInfoResDto = chickenMenuQueryRepositoryImpl.getMenuInfo(menuId).orElseThrow(() -> new CustomException(CustomErrorCode.NotFoundChickenMenuException));
            redisUtil.putString(MENU.prefix() + menuId, chickenMenuInfoResDto, null);

            log.info("==== FROM DB TO REDIS ====");

            return chickenMenuInfoResDto;
        }

    }

    private void setTotalLikeNum(Long menuId, ChickenMenuInfoResDto chickenMenuInfoResDto) {
        Long likeTotalSize = redisUtil.getLikeTotalSize(LIKE.prefix() + menuId);
        chickenMenuInfoResDto.setLikes(likeTotalSize);

    }


}
