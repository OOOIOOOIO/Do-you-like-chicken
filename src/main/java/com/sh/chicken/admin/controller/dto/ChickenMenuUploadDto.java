package com.sh.chicken.admin.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChickenMenuUploadDto {
    private String brandName;
    private String menuName;
    private int price;
    private String contents;

    @Builder
    public ChickenMenuUploadDto(String brandName, String menuName, int price, String contents) {
        this.brandName = brandName;
        this.menuName = menuName;
        this.price = price;
        this.contents = contents;
    }
}
