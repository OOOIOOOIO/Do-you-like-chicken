package com.sh.chicken.domain.chickenmenu.api;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.application.ChickenMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class ChickenMenuApiController {

    private final ChickenMenuService chickenMenuService;

    /**
     * 치킨 메뉴 상세
     */
    @GetMapping("/info/{menuId}")
    public ResponseEntity<ChickenMenuAndLikesResInterface> brandMenuInfo(@PathVariable("menuId") long menuId, Model model){

        ChickenMenuAndLikesResInterface menuInfo = chickenMenuService.getMenuInfo(menuId);

        return new ResponseEntity<>(menuInfo, HttpStatus.OK);

    }
}
