package com.sh.chicken.main.service;

import com.sh.chicken.api.common.dto.ChickenMenusAndTotalLikeResListDto;
import com.sh.chicken.api.main.service.MainService;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class MainServiceTest {

    @Autowired
    MainService mainService;


    @DisplayName("redis에 main chicken menu list 잘 가져오나")
//    @Transactional
    @Test
    public void insertRedisAllChickenMenusWithLikeTest() {
        // given
        ChickenMenusAndTotalLikeResListDto allChickenMenus = mainService.getAllChickenMenus();
        //when
        int size = allChickenMenus.getChicknMenuList().size();

        //then
        Assertions.assertThat(size).isEqualTo(67);


    }


}
