package com.sh.chicken.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSignInReqDto {
    private String username;
    private String pw;
}
