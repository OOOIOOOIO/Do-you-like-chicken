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
    private String accessToken;
    private String refreshToken;

    public UsersSingInResDto(Users users, String accessToken, String refreshToken) {
        this.userId = users.getUserId();
        this.nickname = users.getNickname();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


}
