package com.sh.chicken.domain.user.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersSignUpReqDto {
    private String username;
    private String pw;
    private String nickname;
    private int sex;

    @NotNull
    private Set<String> role;

    public UsersSignUpReqDto(String username, String pw, String nickname, int sex, Set<String> role) {
        this.username = username;
        this.pw = pw;
        this.nickname = nickname;
        this.sex = sex;
        this.role = role;
    }
}
