package com.sh.chicken.domain.chickenlike.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 치킨 메뉴 리스트 제공 api -> 메인화면
 * 치킨 like 순 필터 api -> 메인화면
 * 치킨 클릭 순 필터 api -> 메인화면
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
@RequestMapping("/chicken/like")
public class ChickenLikeApiController {


    @GetMapping("sort/price")
    public ResponseEntity<String> sortByPrice(){

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
