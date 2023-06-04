package com.sh.chicken.web.main.controller.dto;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainResDto {
    private long menuId;
    private String menuName;
    private String brandName;
    private String img;
    private int price;
    private String contents;

    // entity -> dto
    public MainResDto(ChickenMenu chickenMenu) {
        this.menuId = chickenMenu.getMenuId();
        this.menuName = chickenMenu.getMenuName();
        this.brandName = chickenMenu.getChickenBrand().getBrandName();
        this.img = chickenMenu.getImg();
        this.price = chickenMenu.getPrice();
        this.contents = chickenMenu.getContents();
    }
}
