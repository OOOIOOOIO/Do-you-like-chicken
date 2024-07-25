package com.sh.chicken.domain.user.api;

import com.sh.chicken.api.mypage.controller.dto.req.MyPageUpdateReqDto;
import com.sh.chicken.api.mypage.controller.dto.res.MyPageResDto;
import com.sh.chicken.api.mypage.service.MyPageService;
import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.application.UserService;
import com.sh.chicken.global.aop.log.LogTrace;
import com.sh.chicken.global.resolver.UserInfoFromHeader;
import com.sh.chicken.global.resolver.UserInfoFromHeaderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;
    private final MyPageService myPageService;
    /**
     * 회원가입
     */
    @Operation(
            summary = "AccessToken 만료시",
            description = "AccessToken 재발급"
    )
    @ApiResponse(
            responseCode = "200",
            description = "AccessToken 재발급에 성공하였습니다."
    )
    @LogTrace
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UsersSignUpReqDto userSignUpDto) {

        userService.signUp(userSignUpDto);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    /**
     * 로그인
     * Refresh Token 만료시 재로그인
     */
    @LogTrace
    @PostMapping("/signin")
    public ResponseEntity<UsersSingInResDto> signIn(@RequestBody UsersSignInReqDto usersSignInDto, HttpServletRequest request){
        UsersSingInResDto userInfo = userService.signIn(usersSignInDto);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);

    }

    /**
     * 마이페이지
     *
     * 메인
     */
    @LogTrace
    @GetMapping("/main")
    public ResponseEntity<MyPageResDto> getMyChickenLikes(@UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        MyPageResDto myChickenMenuLikes = myPageService.getMyChickenMenuLikes(userInfoFromHeaderDto.getUserId());

        return new ResponseEntity<>(myChickenMenuLikes, HttpStatus.OK);
    }


    /**
     * 마이페이지
     *
     * 닉네임 변경
     */
    @LogTrace
    @PatchMapping("/nickname")
    public ResponseEntity<String> updateNickname(@RequestBody MyPageUpdateReqDto myPageUpdateReqDto, @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        myPageService.updateNickname(userInfoFromHeaderDto.getUserId(), myPageUpdateReqDto.getNickname());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }





}
