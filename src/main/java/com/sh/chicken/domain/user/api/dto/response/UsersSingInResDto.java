package com.sh.chicken.domain.user.api.dto.response;

import com.sh.chicken.domain.user.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSingInResDto {
    private Long userId;
    private String nickname;

    public UsersSingInResDto(Users users) {
        this.userId = users.getUserId();
        this.nickname = users.getNickname();
    }


}
