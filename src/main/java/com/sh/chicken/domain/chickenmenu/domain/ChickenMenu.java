package com.sh.chicken.domain.chickenmenu.domain;

import com.sh.chicken.admin.controller.dto.ChickenMenuUploadDto;
import com.sh.chicken.domain.chickenbrand.domain.ChickenBrand;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChickenMenu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "menu_id")
    private long menuId;

    private String menuName;
    private String img;
    private int price;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brandName")
    private ChickenBrand chickenBrand;

    @OneToMany(mappedBy = "chickenMenu", cascade = CascadeType.REMOVE)
    private List<ChickenLike> chickenLikeList = new ArrayList<>();


    @Builder
    private ChickenMenu(String menuName, int price, String contents, ChickenBrand chickenBrand) {
        this.menuName = menuName;
        this.img = null;
        this.price = price;
        this.contents = contents;
        this.chickenBrand = chickenBrand;
    }

    // 생성 메서드
    public static ChickenMenu createChickenMenu(ChickenMenuUploadDto chickenMenuUploadDto, ChickenBrand chickenBrand){

        return ChickenMenu.builder()
                .menuName(chickenMenuUploadDto.getMenuName())
                .price(chickenMenuUploadDto.getPrice())
                .contents(chickenMenuUploadDto.getContents())
                .chickenBrand(chickenBrand)
                .build();
    }



    // 양방향 연관관계
    public void addChickenLike(ChickenLike chickenLike){

        if (chickenLike.getChickenMenu() != null) {
            chickenLike.getChickenMenu().getChickenLikeList().remove(chickenLike);
        }
        this.chickenLikeList.add(chickenLike);
        chickenLike.setChickenMenu(this);

    }

    public void updateImg(String img){
        this.img = img;
    }



}
