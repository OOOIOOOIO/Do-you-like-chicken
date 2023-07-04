package com.sh.chicken.domain.chickenlike.api;

import com.sh.chicken.api.main.controller.dto.MainResListDto;
import com.sh.chicken.domain.chickenlike.application.ChickenLikeService;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.global.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 치킨 메뉴 정보 제공 api -> 상세화면(댓글은 js로 reload? ready 될 떄 ajax로 가져오기
 *
 * like, 클릭 순 캐싱, 5분? 실시간? 배치?
 *
 * 엄청나게 빠르게 계속 누르면 어떻게 되려나
 *
 * 이미 있다면 추가 안하기
 * 없는데 삭제하면 그냥 넘어가기?
 *
 *
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class ChickenLikeController {

    private final ChickenLikeService chickenLikeApiService;

    @PostMapping("/{menuId}")
    public ResponseEntity<String> add(@PathVariable("menuId") Long menuId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UsersSingInResDto userInfo = (UsersSingInResDto)session.getAttribute(SessionConst.COMMON_USER.getRule());



        chickenLikeApiService.add(menuId, userInfo.getUserId());


        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> delete(@PathVariable("menuId") Long menuId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UsersSingInResDto userInfo = (UsersSingInResDto)session.getAttribute(SessionConst.COMMON_USER.getRule());

        chickenLikeApiService.delete(menuId, userInfo.getUserId());


        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<MainResListDto> sortByLike(){

        MainResListDto chickenMenuList = chickenLikeApiService.getChickenMenusOrderByLikesDesc();

        return new ResponseEntity<>(chickenMenuList, HttpStatus.OK);

    }


}
