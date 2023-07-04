package com.sh.chicken.api.main.controller;

import com.sh.chicken.api.main.controller.dto.MainResListDto;
import com.sh.chicken.api.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(value = {"", "/sort"})
    public ResponseEntity<MainResListDto> getChickenInfo(Model model) {

        MainResListDto chickenMenuList = mainService.getAllChickenMenus();

        return new ResponseEntity<>(chickenMenuList, HttpStatus.OK);
    }


}
