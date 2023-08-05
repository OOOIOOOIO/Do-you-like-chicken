package com.sh.chicken.admin.test;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserLikeDto {

    private Long userId;
    private String username;
    private String pw;
    private String nickname;
    private int sex;

    private List<ChickenLike> chickenLikeList;
    private ChickenMenu chickenMenu;

    public UserLikeDto(Users users) {
        this.userId = users.getUserId();
        this.username = users.getUsername();
        this.pw = users.getPw();
        this.nickname = users.getNickname();
        this.sex = users.getSex();
        this.chickenLikeList = users.getChickenLikeList();
        this.chickenMenu = users.getChickenLikeList().iterator().next().getChickenMenu();
    }
}
