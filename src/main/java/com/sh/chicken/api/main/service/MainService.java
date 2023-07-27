package com.sh.chicken.api.main.service;

import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.api.common.dto.ChickenMenusAndTotalLikeResListDto;
import com.sh.chicken.global.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MainService {

    private final ChickenMenuRepository chickenMenuRepository;
    private final RedisUtil redisUtil;

    public ChickenMenusAndTotalLikeResListDto getAllChickenMenus() {

        if(redisUtil.isExists("main")){
            List<ChickenMenuAndLikesResInterface> getAllChickenMenuWithLikeFromRedis = redisUtil.getByClassType("main", List.class);
            log.info("==== FROM REDIS ====");
            return new ChickenMenusAndTotalLikeResListDto(getAllChickenMenuWithLikeFromRedis);
        }
        else {
            List<ChickenMenuAndLikesResInterface> getAllChickenMenuWithLike = chickenMenuRepository.getAllChickenMenusWithLike();
            redisUtil.putString("main", getAllChickenMenuWithLike, null);
            log.info("==== FROM DB TO REDIS ====");

            return new ChickenMenusAndTotalLikeResListDto(getAllChickenMenuWithLike);
        }
    }


}
