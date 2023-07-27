package com.sh.chicken.api.mypage.controller;


import com.sh.chicken.api.mypage.controller.dto.req.MyPageUpdateReqDto;
import com.sh.chicken.api.mypage.controller.dto.res.MyPageResDto;
import com.sh.chicken.api.mypage.service.MyPageService;
import com.sh.chicken.global.aop.log.LogTrace;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSession;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @LogTrace
    @PatchMapping("/info/update")
    public ResponseEntity<String> updateNickname(@RequestBody MyPageUpdateReqDto myPageUpdateReqDto, @UserInfoFromSession UserInfoFromSessionDto userInfoFromSessionDto){
        myPageService.updateNickname(userInfoFromSessionDto.getUserId(), myPageUpdateReqDto.getNickname());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // nickname 수정 후 다시 api 요청해야하지 않을까 --> 회의 때

    @LogTrace
    @GetMapping("/like")
    public ResponseEntity<MyPageResDto> getMyChickenLikes(@UserInfoFromSession UserInfoFromSessionDto userInfoFromSessionDto){
        MyPageResDto myChickenMenuLikes = myPageService.getMyChickenMenuLikes(userInfoFromSessionDto.getUserId());

        return new ResponseEntity<>(myChickenMenuLikes, HttpStatus.OK);
    }
}
