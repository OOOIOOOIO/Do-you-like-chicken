package com.sh.chicken.web.user;

import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.application.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    /**
     * 회원가입, 로그인 binding result 해주기
     * 로그인에 세션 설정해주기
     * 로그아웃 개발
     */
    private final UsersService usersService;

    // 회원가입
    @GetMapping("/signup")
    public String signUp() {

        return "/users/sign-up";
    }

    // 로그인
    @GetMapping("/signin")
    public String signIn(){

        return "/users/sign-in";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("userSignUpDto") UsersSignUpReqDto userSignUpDto){

        log.info(userSignUpDto.getUsername());
        log.info(userSignUpDto.getPw());
        log.info(userSignUpDto.getNickname());
        boolean isDuplicate = usersService.checkDuplicateUsername(userSignUpDto.getUsername());

        if(!isDuplicate){
            usersService.signUp(userSignUpDto);
            return "/users/sign-in";
        }

        return "/users/sign-up";

    }

    // 로그인
    @PostMapping("/signin")
    public String signIn(@ModelAttribute("usersSignInDto") UsersSignInReqDto usersSignInDto){
        log.info("====="+usersSignInDto.getUsername() + "==" + usersSignInDto.getPw());

        usersService.signIn(usersSignInDto);

        return "/main";

    }

    // 로그아웃
    public void signOut(){
        // 세션 삭제 -> 스프링 세션 공부해서 그거 써보자
    }


}
