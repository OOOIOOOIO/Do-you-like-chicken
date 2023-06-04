package com.sh.chicken.domain.chickenlike.domain;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ck_like")
public class ChickenLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private ChickenMenu chickenMenu;

    @Builder
    private ChickenLike(Users users, ChickenMenu chickenMenu) {
        this.users = users;
        this.chickenMenu = chickenMenu;
    }

    // 생성 메서드
    public static ChickenLike createChickenLike(Users users, ChickenMenu chickenMenu){
        return ChickenLike.builder()
                .users(users)
                .chickenMenu(chickenMenu)
                .build();
    }


}
