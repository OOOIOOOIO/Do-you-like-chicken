package com.sh.chicken.admin.application;

import com.sh.chicken.admin.controller.dto.ChickenMenuUploadDto;
import com.sh.chicken.admin.repository.ChickenBrandUploadRepository;
import com.sh.chicken.admin.repository.ChickenMenuUploadRepository;
import com.sh.chicken.domain.chickenbrand.domain.ChickenBrand;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.global.util.aws.s3.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChickenMenuUploadService {

    private final ChickenMenuUploadRepository chickenMenuUploadRepository;
    private final ChickenBrandUploadRepository chickenBrandUploadRepository;
    private final AmazonS3Service amazonS3Service;

    /**
     * 메뉴 정보 저장
     */
    public String saveMenu(List<ChickenMenuUploadDto> dataList) {

        for (ChickenMenuUploadDto chickenMenuUploadDto : dataList) {
            ChickenBrand chickenBrand = chickenBrandUploadRepository.findByBrandName(chickenMenuUploadDto.getBrandName()).get();

            ChickenMenu chickenMenu = ChickenMenu.createChickenMenu(chickenMenuUploadDto, chickenBrand);

            chickenMenuUploadRepository.save(chickenMenu);
        }
        return "success";

    }

    /**
     * 메뉴 사진 업데이트
     */
    public void uploadFile(String menuName, String brandName, MultipartFile file){
        String img = amazonS3Service.uploadFile(file);
        log.info("dd" + brandName);

        ChickenBrand chickenBrand = chickenBrandUploadRepository.findByBrandName(brandName).orElseThrow(() -> new RuntimeException("브랜드없음"));

        ChickenMenu chickenMenu = chickenMenuUploadRepository.findByMenuNameAndChickenBrand(menuName, chickenBrand).orElseThrow(() -> new RuntimeException("img"));

//        log.info(chickenMenu.getMenuName());
        chickenMenu.updateImg(img); // 더티 체킹

    }



}
