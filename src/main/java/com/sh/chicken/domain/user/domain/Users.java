package com.sh.chicken.domain.user.domain;

import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String username;
    private String pw;
    private String nickname;

    @Builder
    private Users(String username, String pw, String nickname) {
        this.username = username;
        this.pw = pw;
        this.nickname = nickname;
    }

    public static Users createUser(UsersSignUpReqDto userSignUpDto){

        return Users.builder()
                .username(userSignUpDto.getUsername())
                .pw(userSignUpDto.getPw())
                .nickname(userSignUpDto.getNickname())
                .build();

    }
}
