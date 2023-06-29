package com.sh.chicken.domain.chickenmenu.api.dto;


import lombok.Getter;

@Getter
public class ChickenMenuAndLikesDto {

    private long menuId;
    private String menuName;
    private String brandName;
    private String img;
    private int price;
    private String contents;

    private int likes;

    public ChickenMenuAndLikesDto(long menuId, String menuName, String brandName, String img, int price, String contents, int likes) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.brandName = brandName;
        this.img = img;
        this.price = price;
        this.contents = contents;
        this.likes = likes;
    }
}
