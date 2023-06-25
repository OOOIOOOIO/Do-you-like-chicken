package com.sh.chicken.api.main.controller;

import com.sh.chicken.api.main.controller.dto.MainResListDto;
import com.sh.chicken.api.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final MainService mainService;

    /**
     * Main Page, 가격순 정렬
     */
    @GetMapping("")
    public ResponseEntity<MainResListDto> getChickenInfo(Model model) {

        MainResListDto chickenMenuList = mainService.getChickenMenus();

        model.addAttribute("chickenMenuList", chickenMenuList.getChicknMenuList());

//        for (MainResDto mainResDto : chickenMenuList.getMainResDtoList()) {
//            log.info("==="+mainResDto.getMenuId());
//            log.info("==="+mainResDto.getBrandName());
//            log.info("==="+mainResDto.getMenuName());
//            log.info("==="+mainResDto.getContents());
//            log.info("==="+mainResDto.getImg());
//            log.info("==="+mainResDto.getPrice());
//        }

        return new ResponseEntity<>(chickenMenuList, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public void sortByPrice(){

    }
}
