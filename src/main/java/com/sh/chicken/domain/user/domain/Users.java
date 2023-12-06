package com.sh.chicken.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.common.BaseTimeEntity;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class Users extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 50)
    private String username;

//    @NotBlank
//    @Size(max = 50)
//    private String email;

    @NotBlank
    @Size(max = 120)
    private String pw;

    private int sex;
    private String nickname;


    @JsonManagedReference
    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<ChickenLike> chickenLikeList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @Builder
    private Users(String username, String pw, String nickname, int sex) {
        this.username = username;
        this.pw = pw;
        this.nickname = nickname;
        this.sex = sex;


    }

    public static Users createUser(UsersSignUpReqDto userSignUpDto, String pw){
        return Users.builder()
                .username(userSignUpDto.getUsername())
                .pw(pw)
                .nickname(userSignUpDto.getNickname())
                .sex(userSignUpDto.getSex())
                .build();
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void changeNickname(String nickname){
        this.nickname = nickname;

    }
}
