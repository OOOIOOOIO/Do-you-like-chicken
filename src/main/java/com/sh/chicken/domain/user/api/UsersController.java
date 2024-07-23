package com.sh.chicken.domain.user.api;

import com.sh.chicken.api.jwt.controller.dto.AccessTokenResponseDto;
import com.sh.chicken.api.mypage.controller.dto.req.MyPageUpdateReqDto;
import com.sh.chicken.api.mypage.controller.dto.res.MyPageResDto;
import com.sh.chicken.api.mypage.service.MyPageService;
import com.sh.chicken.domain.chickenlike.application.ChickenLikeService;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.application.UserService;
import com.sh.chicken.global.aop.log.LogTrace;
import com.sh.chicken.global.resolver.UserInfoFromHeader;
import com.sh.chicken.global.resolver.UserInfoFromHeaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;
    private final ChickenLikeService chickenLikeService;
    private final MyPageService myPageService;
    /**
     * 회원가입
     */
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
        HttpSession session = request.getSession(true);

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
