package com.sh.chicken.menu.repository;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuQueryRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class ChickenMenuRepositoryCustomTest {

    @Autowired
    ChickenMenuQueryRepositoryImpl chickenMenuQueryRepositoryImpl;


    @DisplayName("get all chicken menus with total like")
    @Test
    public void getAllMenusWithTotalLike(){
        // given
        List<ChickenMenuInfoResDto> allMenus = chickenMenuQueryRepositoryImpl.getAllMenusWithTotalLikeLikesDesc();

        // when-then
        assertThat(allMenus.size()).isEqualTo(67);
        assertThat(allMenus.get(0).getMenuId()).isEqualTo(1);
        assertThat(allMenus.get(0).getMenuName()).isEqualTo("고추바사삭");
        assertThat(allMenus.get(0).getBrandName()).isEqualTo("굽네치킨");
        assertThat(allMenus.get(0).getLikes()).isEqualTo(8630);

    }

    @DisplayName("get all chicken menus")
    @Test
    public void getAllMenus(){
        // given
        List<ChickenMenuInfoResDto> allMenus = chickenMenuQueryRepositoryImpl.getAllMenus();

        // when-then
        assertThat(allMenus.size()).isEqualTo(67);
        assertThat(allMenus.get(0).getMenuId()).isEqualTo(1);
        assertThat(allMenus.get(0).getMenuName()).isEqualTo("고추바사삭");
        assertThat(allMenus.get(0).getBrandName()).isEqualTo("굽네치킨");
        assertThat(allMenus.get(0).getPrice()).isEqualTo(18000);
        assertThat(allMenus.get(0).getLikes()).isEqualTo(8630);

    }


    @Test
    public void getMenuInfo(){
        // given
        Long menuId = 1L;

        // when
        ChickenMenuInfoResDto chickenMenuInfoResDto = chickenMenuQueryRepositoryImpl.getMenuInfo(menuId).get();

        // then
        assertThat(chickenMenuInfoResDto.getMenuId()).isEqualTo(1);
        assertThat(chickenMenuInfoResDto.getBrandName()).isEqualTo("굽네치킨");
        assertThat(chickenMenuInfoResDto.getMenuName()).isEqualTo("고추바사삭");
        assertThat(chickenMenuInfoResDto.getPrice()).isEqualTo(18000);

//        assertThat(chickenMenuInfoResDto.getLikes()).isEqualTo(8630);
    }
}
