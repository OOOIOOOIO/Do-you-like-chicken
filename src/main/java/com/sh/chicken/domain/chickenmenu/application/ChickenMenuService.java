package com.sh.chicken.domain.chickenmenu.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.global.aop.log.LogTrace;
import com.sh.chicken.global.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChickenMenuService {

    private final ChickenMenuRepository chickenMenuRepository;
    private final RedisUtil redisUtil;
    private final static String MENU = "MENU";


    public ChickenMenuInfoResDto getMenuInfo(long menuId) {

        if (redisUtil.isExists(MENU + menuId)) {
            ChickenMenuInfoResDto chickenMenuFromRedis = redisUtil.getByClassType(MENU + menuId, ChickenMenuInfoResDto.class);
            log.info("==== FROM REDIS ====");

            return chickenMenuFromRedis;
        } else {
            ChickenMenuAndLikesResInterface chickenMenuInterface = chickenMenuRepository.findMenuAndLikesByMenuId(menuId).orElseThrow(() -> new RuntimeException("메뉴 없음"));
            ChickenMenuInfoResDto chickenMenu = new ChickenMenuInfoResDto(chickenMenuInterface);
            redisUtil.putString(MENU+menuId, chickenMenu, null);
            log.info("==== FROM DB TO REDIS ====");

            return chickenMenu;
        }

    }


}
