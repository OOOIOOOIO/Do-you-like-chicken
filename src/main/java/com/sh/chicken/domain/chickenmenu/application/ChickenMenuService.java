package com.sh.chicken.domain.chickenmenu.application;

import com.sh.chicken.domain.common.dto.ChickenMenusAndTotalLikeResListDto;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
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

    public ChickenMenusAndTotalLikeResListDto getChickenMenusOrderByLikesDesc(){
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.getAllChickenMenusWithLikeOrderByLikesDESC();

        return new ChickenMenusAndTotalLikeResListDto(chickenMenuBySelectSubQuery);
    }


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
