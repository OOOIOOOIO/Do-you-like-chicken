package com.sh.chicken.api.mypage.controller.dto.res;

import com.sh.chicken.domain.user.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersInfoResDto {

    private long userId;
    private String nickname;
    private String username;
    private int sex;

    public UsersInfoResDto(Users users) {
        this.userId = users.getUserId();
        this.nickname = users.getNickname();
        this.username = users.getUsername();
        this.sex = users.getSex();
    }


}

