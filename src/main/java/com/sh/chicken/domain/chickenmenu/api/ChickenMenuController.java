package com.sh.chicken.domain.chickenmenu.api;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResListDto;
import com.sh.chicken.domain.chickenmenu.application.ChickenMenuService;
import com.sh.chicken.global.aop.log.LogTrace;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiOperation(value = "치킨 메뉴 가격순 리스트 조회 api", notes = "치킨 메뉴들을 가격순으로 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @LogTrace
    @GetMapping(value = {"", "/sort"})
    public ResponseEntity<ChickenMenuInfoResListDto> getChickenInfo() {

        ChickenMenuInfoResListDto chickenMenuList = chickenMenuService.getAllChickenMenus();

        log.info("============== Main, Sort ============");
        return new ResponseEntity<>(chickenMenuList, HttpStatus.OK);
    }

    @ApiOperation(value = "치킨 메뉴 좋아요순 리스트 조회 api", notes = "치킨 메뉴들을 좋아요순으로 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @LogTrace
    @GetMapping("/sort/likes")
    public ResponseEntity<ChickenMenuInfoResListDto> getChickenInfoSortByLike(){
        ChickenMenuInfoResListDto allChickenMenus = chickenMenuService.getChickenMenusOrderByLikesDesc();

        return new ResponseEntity<>(allChickenMenus, HttpStatus.OK);
    }

    @ApiOperation(value = "치킨 메뉴 정보 조회 api", notes = "치킨 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @LogTrace
    @GetMapping("/info/{menuId}")
    public ResponseEntity<ChickenMenuInfoResDto> brandMenuInfo(@PathVariable("menuId") long menuId){
        ChickenMenuInfoResDto menuInfoQuerydsl = chickenMenuService.getMenuInfo(menuId);


        return new ResponseEntity<>(menuInfoQuerydsl, HttpStatus.OK);

    }
}
