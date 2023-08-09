package com.sh.chicken.domain.chickenmenu.api.dto.res;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class  ChickenMenuInfoResDto{

    private long menuId;
    private String menuName;
    private String brandName;
    private String img;
    private int price;
    private String contents;

    private Long likes;

    public ChickenMenuInfoResDto(long menuId, String menuName, String brandName, String img, int price, String contents) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.brandName = brandName;
        this.img = img;
        this.price = price;
        this.contents = contents;
    }

    public ChickenMenuInfoResDto(long menuId, String menuName, String brandName, String img, int price, String contents, Long likes) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.brandName = brandName;
        this.img = img;
        this.price = price;
        this.contents = contents;
        this.likes = likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}
