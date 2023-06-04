package com.sh.chicken.admin.application;

import com.sh.chicken.admin.controller.dto.ChickenMenuUploadDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Slf4j
@SpringBootTest
@Transactional
//@ExtendWith(MockitoExtension.class)
public class ChickenMenuUploadServiceTest {

//    @InjectMocks
    @Autowired
    ChickenMenuUploadService chickenMenuUploadService;


    @Test
    public void saveMenu() {
        // given
        ArrayList<ChickenMenuUploadDto> dataList = new ArrayList<>();

        createMenu(dataList);

        // mocking
//        given(chickenMenuUploadService.saveMenu(any())).willReturn("success");

        // when
        String result = chickenMenuUploadService.saveMenu(dataList);

        // then
        Assertions.assertThat(result).isEqualTo("success");

    }


    private static void createMenu(ArrayList<ChickenMenuUploadDto> dataList) {
        ChickenMenuUploadDto menu1 = new ChickenMenuUploadDto("교촌치킨", "레드콤보", 23000, "매우 맛있게 맵다");
        ChickenMenuUploadDto menu2 = new ChickenMenuUploadDto("BBQ", "황금올리브치킨", 20000, "바삭하다");
        ChickenMenuUploadDto menu3 = new ChickenMenuUploadDto("BHC", "뿌링클", 18000, "고소하다");
        ChickenMenuUploadDto menu4 = new ChickenMenuUploadDto("교촌치킨", "허니콤보", 23000, "달다");
        ChickenMenuUploadDto menu5 = new ChickenMenuUploadDto("BBQ", "자메이카 통다리구이", 21500, "맛있다");
        ChickenMenuUploadDto menu6 = new ChickenMenuUploadDto("BHC", "맛초킹", 18000, "간장맛이다");

        dataList.add(menu1);
        dataList.add(menu2);
        dataList.add(menu3);
        dataList.add(menu4);
        dataList.add(menu5);
        dataList.add(menu6);
    }


}
