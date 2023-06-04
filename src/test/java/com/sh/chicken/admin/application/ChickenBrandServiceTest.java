package com.sh.chicken.admin.application;

import com.sh.chicken.admin.controller.dto.ChickenBrandUploadDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Slf4j
@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
public class ChickenBrandServiceTest {

    @InjectMocks
    ChickenBrandUploadService chickenBrandUploadService;

    @Test
    public void brandUploadService() {
        // given
        String[] brands = new String[]{"교촌치킨", "BBQ", "BHC"};
        ArrayList<ChickenBrandUploadDto> dataList = new ArrayList<>();

        for (String brandName : brands) {
            ChickenBrandUploadDto brand = new ChickenBrandUploadDto(brandName);
            dataList.add(brand);

        }

        // when
        chickenBrandUploadService.saveBrand(dataList);


    }


}
