package com.sh.chicken.api.mypage.controller.dto.req;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPageUpdateReqDto {

    private String nickname;

    public MyPageUpdateReqDto(String nickname) {
        this.nickname = nickname;
    }
}
