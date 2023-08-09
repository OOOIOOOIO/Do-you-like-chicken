package com.sh.chicken.admin.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChickenMenuImgUploadDto {
    private String brandName;
    private String menuName;
    private int price;
    private String contents;
    private String img;

    @Builder
    public ChickenMenuImgUploadDto(String brandName, String menuName, int price, String contents, String img) {
        this.brandName = brandName;
        this.menuName = menuName;
        this.price = price;
        this.contents = contents;
        this.img = img;
    }
}
