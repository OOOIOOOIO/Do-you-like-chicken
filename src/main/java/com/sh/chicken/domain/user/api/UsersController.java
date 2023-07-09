package com.sh.chicken.domain.user.api;

import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.application.UsersService;
import com.sh.chicken.global.common.SessionConst;
import com.sh.chicken.global.aop.log.LogTrace;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @LogTrace
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UsersSignUpReqDto userSignUpDto) {

        boolean isDuplicate = usersService.checkDuplicateUsername(userSignUpDto.getUsername());

        if (isDuplicate) {
            return new ResponseEntity<>("duplicate", HttpStatus.BAD_REQUEST); // 추후 수정
        }

        usersService.signUp(userSignUpDto);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    // 로그인
    @LogTrace
    @PostMapping("/signin")
    public ResponseEntity<UserInfoFromSessionDto> signIn(@RequestBody UsersSignInReqDto usersSignInDto, HttpServletRequest request){
        HttpSession session = request.getSession(true);

        UserInfoFromSessionDto userInfo = usersService.signIn(usersSignInDto);

        session.setAttribute(SessionConst.COMMON_USER.getRule(), userInfo);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);

    }

    // 로그아웃
    public ResponseEntity<String> signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);


        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
