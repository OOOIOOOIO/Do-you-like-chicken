package com.sh.chicken.domain.chickenmenu.api;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.application.ChickenMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Model 객체는 view에 담아 보낼 데이터
 * @ModelAttribute는 Http body 및 파라미터를 dto에 바인딩하기 위해 사용
 */

@RestController("/api/menu")
@RequiredArgsConstructor
public class ChickenMenuApiController {

    private final ChickenMenuService chickenMenuService;
    /**
     * 치킨 메뉴 상세
     */
    @GetMapping("/info")
    public String brandMenuInfo(@RequestParam("menuId") long menuId, Model model){

        ChickenMenuInfoResDto menuInfo = chickenMenuService.getMenuInfo(menuId);

        model.addAttribute("menuInfo", menuInfo);


        return "menu-info";
    }
}
