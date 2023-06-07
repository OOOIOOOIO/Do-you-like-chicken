package com.sh.chicken.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UsersSignInReqDto {
    private String username;
    private String pw;

    public UsersSignInReqDto(String username, String pw) {
        this.username = username;
        this.pw = pw;
    }
}
