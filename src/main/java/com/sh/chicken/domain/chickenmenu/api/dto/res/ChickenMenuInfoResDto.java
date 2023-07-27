package com.sh.chicken.domain.chickenmenu.api.dto.res;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChickenMenuInfoResDto {

    private long menuId;
    private String menuName;
    private String brandName;
    private String img;
    private int price;
    private String contents;
    private int likes;

    // projection interface -> dto
    public ChickenMenuInfoResDto(ChickenMenuAndLikesResInterface chickenMenuAndLikesResInterface) {
        this.menuId = chickenMenuAndLikesResInterface.getMenuId();
        this.menuName = chickenMenuAndLikesResInterface.getMenuName();
        this.brandName = chickenMenuAndLikesResInterface.getBrandName();
        this.img = chickenMenuAndLikesResInterface.getImg();
        this.price = chickenMenuAndLikesResInterface.getPrice();
        this.contents = chickenMenuAndLikesResInterface.getContents();
        this.likes = chickenMenuAndLikesResInterface.getLikes();
    }
}
