package com.sh.chicken.global.resolver;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoFromHeaderDto {
    private Long userId;
    private String email;

    public UserInfoFromHeaderDto(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
