package com.sh.chicken.domain.chickenmenu.api;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResListDto;
import com.sh.chicken.domain.chickenmenu.application.ChickenMenuService;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class ChickenMenuController {

    private final ChickenMenuService chickenMenuService;

    /**
     * Main Page, 가격순 정렬
     */
    @LogTrace
    @GetMapping(value = {"", "/sort"})
    public ResponseEntity<ChickenMenuInfoResListDto> getChickenInfo() {

        ChickenMenuInfoResListDto chickenMenuList = chickenMenuService.getAllChickenMenus();

        log.info("============== Main, Sort ============");
        return new ResponseEntity<>(chickenMenuList, HttpStatus.OK);
    }

    @LogTrace
    @GetMapping("/sort/likes")
    public ResponseEntity<ChickenMenuInfoResListDto> sortByLike(){
        ChickenMenuInfoResListDto allChickenMenus = chickenMenuService.getChickenMenusOrderByLikesDesc();

        return new ResponseEntity<>(allChickenMenus, HttpStatus.OK);
    }

    @LogTrace
    @GetMapping("/info/{menuId}")
    public ResponseEntity<ChickenMenuInfoResDto> brandMenuInfo(@PathVariable("menuId") long menuId){
        ChickenMenuInfoResDto menuInfoQuerydsl = chickenMenuService.getMenuInfo(menuId);


        return new ResponseEntity<>(menuInfoQuerydsl, HttpStatus.OK);

    }
}
