package com.sh.chicken.domain.chickenlike.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChickenLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private long likeId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private ChickenMenu chickenMenu;

    @Builder
    private ChickenLike(Users users, ChickenMenu chickenMenu) {
        this.users = users;
        this.chickenMenu = chickenMenu;
    }

    /**
     * 생성 메서드
     * @param users
     * @param chickenMenu
     * @return
     */
    public static ChickenLike createChickenLike(Users users, ChickenMenu chickenMenu){
        return ChickenLike.builder()
                .users(users)
                .chickenMenu(chickenMenu)
                .build();
    }

    public void setChickenMenu(ChickenMenu chickenMenu){
        this.chickenMenu = chickenMenu;

    }


}
