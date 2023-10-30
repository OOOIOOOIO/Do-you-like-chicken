package com.sh.chicken.global.session.resolver.usersession;

import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserInfoFromSessionDto {

    private Long userId;
    private String nickname;

    public UserInfoFromSessionDto(UsersSingInResDto usersSingInResDto) {
        this.userId = usersSingInResDto.getUserId();
        this.nickname = usersSingInResDto.getNickname();
    }
}
