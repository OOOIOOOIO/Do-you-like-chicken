package com.sh.chicken.menu.service;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResListDto;
import com.sh.chicken.domain.common.dto.ChickenMenusAndTotalLikeResListDto;
import com.sh.chicken.domain.chickenmenu.application.ChickenMenuService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class ChickenMenuServiceTest {

    @Autowired
    ChickenMenuService chickenMenuService;


    @DisplayName("redis에 main chicken menu list 잘 가져오나")
//    @Transactional
    @Test
    public void insertRedisAllChickenMenusWithLikeTest() {
        // given
        ChickenMenuInfoResListDto allChickenMenus = chickenMenuService.getAllChickenMenus();
        //when
        int size = allChickenMenus.getChickenMenuInfoResDto().size();

        //then
        Assertions.assertThat(size).isEqualTo(67);


    }


}
