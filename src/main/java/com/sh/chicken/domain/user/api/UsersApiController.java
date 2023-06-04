package com.sh.chicken.domain.user.api;

import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.application.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersApiController {

    private final UsersService usersService;


    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(UsersSignUpReqDto userSignUpDto){

        boolean isValid = usersService.checkDuplicateUsername(userSignUpDto.getUsername());

        if(isValid){
            usersService.signUp(userSignUpDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }

        return new ResponseEntity<>("duplicate", HttpStatus.OK);

    }

    // 로그인
    @PostMapping("/signin")
    public void signIn(UsersSignInReqDto usersSignInDto){
        log.info("====="+usersSignInDto.getUsername() + "==" + usersSignInDto.getPw());

        usersService.signIn(usersSignInDto);

    }

    // 로그아웃
    public void signOut(){
        // 세션 삭제 -> 스프링 세션 공부해서 그거 써보자
    }
}
