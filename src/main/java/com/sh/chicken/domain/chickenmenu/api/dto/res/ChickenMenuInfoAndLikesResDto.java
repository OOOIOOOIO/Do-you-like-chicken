package com.sh.chicken.domain.chickenmenu.api.dto.res;

import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChickenMenuInfoAndLikesResDto {

    private long menuId;
    private String menuName;
    private String brandName;
    private String img;
    private int price;
    private String contents;
    private int likes;

    // projection interface -> dto
    public ChickenMenuInfoAndLikesResDto(ChickenMenuAndLikesResInterface chickenMenuAndLikesResInterface) {
        this.menuId = chickenMenuAndLikesResInterface.getMenuId();
        this.menuName = chickenMenuAndLikesResInterface.getMenuName();
        this.brandName = chickenMenuAndLikesResInterface.getBrandName();
        this.img = chickenMenuAndLikesResInterface.getImg();
        this.price = chickenMenuAndLikesResInterface.getPrice();
        this.contents = chickenMenuAndLikesResInterface.getContents();
        this.likes = chickenMenuAndLikesResInterface.getLikes();
    }


}
