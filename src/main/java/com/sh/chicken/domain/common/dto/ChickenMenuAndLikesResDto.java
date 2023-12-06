package com.sh.chicken.domain.common.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChickenMenuAndLikesResDto {
    private Long menuId;
    private String menuName;
    private String brandName;
    private String img;
    private Integer price;
    private String contents;
    private Long likes;

    public ChickenMenuAndLikesResDto(Long menuId, String menuName, String brandName, String img, Integer price, String contents, Long likes) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.brandName = brandName;
        this.img = img;
        this.price = price;
        this.contents = contents;
        this.likes = likes;
    }
}
