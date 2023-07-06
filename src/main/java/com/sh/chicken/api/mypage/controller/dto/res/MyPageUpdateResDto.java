package com.sh.chicken.api.mypage.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sh.chicken.domain.user.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPageUpdateResDto {

    private String nickname;

    @JsonCreator
    public MyPageUpdateResDto(Users users) {
        this.nickname = users.getNickname();
    }
}
