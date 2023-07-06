package com.sh.chicken.api.mypage.controller;


import com.sh.chicken.api.mypage.controller.dto.req.MyPageUpdateReqDto;
import com.sh.chicken.api.mypage.controller.dto.res.MyPageResDto;
import com.sh.chicken.api.mypage.service.MyPageService;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.global.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @PatchMapping("/info/update")
    public ResponseEntity<String> updateNickname(@RequestBody MyPageUpdateReqDto myPageUpdateReqDto, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        UsersSingInResDto userInfo = (UsersSingInResDto)session.getAttribute(SessionConst.COMMON_USER.getRule());
        log.info("================" + myPageUpdateReqDto.getNickname());
        myPageService.updateNickname(userInfo.getUserId(), myPageUpdateReqDto.getNickname());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // nickname 수정 후 다시 api 요청해야하지 않을까 --> 회의 때

    @GetMapping("/like")
    public ResponseEntity<MyPageResDto> getMyChickenLikes(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        UsersSingInResDto userInfo = (UsersSingInResDto)session.getAttribute(SessionConst.COMMON_USER.getRule());

        MyPageResDto myChickenMenuLikes = myPageService.getMyChickenMenuLikes(userInfo.getUserId());


        return new ResponseEntity<>(myChickenMenuLikes, HttpStatus.OK);

    }
}
