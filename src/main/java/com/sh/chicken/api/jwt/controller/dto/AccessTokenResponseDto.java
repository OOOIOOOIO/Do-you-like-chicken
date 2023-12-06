package com.sh.chicken.api.jwt.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenResponseDto {

    private String AccessToken;
//    private String contextPath;


    public AccessTokenResponseDto(String accessToken) {
        AccessToken = accessToken;
    }
}
