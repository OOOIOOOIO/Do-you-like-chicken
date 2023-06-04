package com.sh.chicken.web.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

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


}
