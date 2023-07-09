package com.sh.chicken.admin.chickenBrand.repository;

import com.sh.chicken.domain.chickenbrand.domain.repository.ChickenBrandRepository;
import com.sh.chicken.domain.chickenbrand.domain.ChickenBrand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ChickenBrandUpladRepositoryTest {

    @Autowired
    ChickenBrandRepository chickenBrandUploadRepository;

    @Test
    public void findChickenByBrandName(){
        // given
        String[] brands = new String[]{"교촌치킨", "BBQ", "BHC"};

        // when
        for (String brand : brands) {
            ChickenBrand chickenBrand = chickenBrandUploadRepository.findByBrandName(brand).get();
            log.info("[Brand name] : {}", chickenBrand.getBrandName());
        }

        // then

    }

    
    
}
