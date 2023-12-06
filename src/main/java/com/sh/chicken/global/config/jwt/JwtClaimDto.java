package com.sh.chicken.global.config.jwt;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtClaimDto {

    private Long userId;
    private String username;
    private String tokenType;


    public JwtClaimDto(Long userId, String username, String tokenType) {
        this.userId = userId;
        this.username = username;
        this.tokenType = tokenType;
    }

}
