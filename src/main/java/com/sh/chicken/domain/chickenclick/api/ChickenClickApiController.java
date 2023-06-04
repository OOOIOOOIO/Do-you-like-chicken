package com.sh.chicken.domain.chickenclick.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 치킨 메뉴 리스트 제공 api -> 메인화면
 * 치킨 like 순 필터 api -> 메인화면
 * 치킨 클릭 순 필터 api -> 메인화면
 * 치킨 메뉴 정보 제공 api -> 상세화면(댓글은 js로 reload? ready 될 떄 ajax로 가져오기
 * 치킨 좋아요 api -> 상태변경
 *
 * like, 클릭 순 캐싱, 5분? 실시간? 배치?
 *
 * Model 객체는 view에 담아 보낼 데이터
 * @ModelAttribute는 Http body 및 파라미터를 dto에 바인딩하기 위해 사용
 */

@Controller
public class ChickenClickApiController {


    public String move(){
        return "temp";
    }
    @ResponseBody // rest 방식 -> ajax로 바로 데이터 줄 때
    public ResponseEntity<String> temp(){

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
