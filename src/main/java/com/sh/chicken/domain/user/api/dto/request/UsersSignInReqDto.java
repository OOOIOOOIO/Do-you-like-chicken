package com.sh.chicken.domain.user.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersSignInReqDto {
    private String username;
    private String pw;

    public UsersSignInReqDto(String username, String pw) {
        this.username = username;
        this.pw = pw;
    }
}
