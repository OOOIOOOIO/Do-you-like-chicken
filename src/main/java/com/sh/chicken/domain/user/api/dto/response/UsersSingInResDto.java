package com.sh.chicken.domain.user.api.dto.response;

import com.sh.chicken.domain.user.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersSingInResDto {
    private Long userId;
    private String nickname;

    public UsersSingInResDto(Users users) {
        this.userId = users.getUserId();
        this.nickname = users.getNickname();
    }


}
