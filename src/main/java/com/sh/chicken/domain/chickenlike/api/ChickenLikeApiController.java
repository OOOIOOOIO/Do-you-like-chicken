package com.sh.chicken.domain.chickenlike.api;

import com.sh.chicken.domain.chickenlike.api.dto.request.ChickenLikeChangeReqDto;
import com.sh.chicken.domain.chickenlike.application.ChickenLikeApiService;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.global.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/api/chicken/like")
public class ChickenLikeApiController {

    private final ChickenLikeApiService chickenLikeApiService;


    @GetMapping("/sort")
    public ResponseEntity<String> sortByLike(){

        return null;
    }

    /**
     * 일단 캐싱 기능을 하기 위해 개발을 하는 것이기 때문에
     * 없다면 insert
     * 있다면 delete 해주겠다.
     */
    @PostMapping("/change")
    public ResponseEntity<String> change(@RequestParam("menuId") Long menuId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Users users = (Users)session.getAttribute(SessionConst.COMMON_USER.getRule());
        Long userId = users.getUserId();

        chickenLikeApiService.addOrDelete(menuId, userId);


        return null;
    }
    @PostMapping("/add")
    public ResponseEntity<String> add() {

        return null;
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(){

        return null;
    }

}
