package com.sh.chicken.api.jwt.controller;

import com.sh.chicken.api.jwt.controller.dto.AccessTokenResponseDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.application.UserService;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
@RequestMapping("/api/issue")
public class TokenIssueController {

    private final UserService userService;

    /**
     * AccessToken 만료시 재발급
     *
     */
    @LogTrace
    @PostMapping("/access-token")
    public ResponseEntity<AccessTokenResponseDto> reIssueAccessToken(HttpServletRequest request){

        AccessTokenResponseDto accessTokenResponseDto = userService.reIssueAccessToken(request);

        return new ResponseEntity<>(accessTokenResponseDto, HttpStatus.OK);
    }


}
