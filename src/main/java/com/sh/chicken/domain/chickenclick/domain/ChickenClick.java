package com.sh.chicken.domain.chickenclick.domain;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChickenClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long click_id;

    private long cnt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private ChickenMenu chickenMenu;

    @Builder
    private ChickenClick(long cnt, ChickenMenu chickenMenu) {
        this.cnt = cnt;
        this.chickenMenu = chickenMenu;
    }

    // 생성 메서드
    public static ChickenClick createOrUpdateChickenClick(long cnt, ChickenMenu chickenMenu){
        return ChickenClick.builder()
                .cnt(cnt)
                .chickenMenu(chickenMenu)
                .build();

    }

    // 수정
    public void addClickCount(String plus){
        this.cnt = ++this.cnt;

    }

}
