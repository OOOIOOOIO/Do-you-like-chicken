package com.sh.chicken.web.chickenmenuinfo.controller;


import com.sh.chicken.web.chickenmenuinfo.controller.dto.ChickenMenuInfoResDto;
import com.sh.chicken.web.chickenmenuinfo.service.ChickenMenuInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chicken/menu/info")
public class ChickenMenuInfoController {

    private final ChickenMenuInfoService chickenMenuInfoService;
    /**
     * 각 브랜드의 치킨 메뉴 Page
     */
    @GetMapping("")
    public String brandMenuList(@RequestParam("menuId") long menuId, Model model){

        ChickenMenuInfoResDto menuInfo = chickenMenuInfoService.getMenuInfo(menuId);

        model.addAttribute("menuInfo", menuInfo);


        return "menu-info";
    }
}
