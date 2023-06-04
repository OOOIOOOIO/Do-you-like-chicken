package com.sh.chicken.admin.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChickenBrandUploadDto {

    private String brandName;

    @Builder
    public ChickenBrandUploadDto(String brandName) {
        this.brandName = brandName;
    }
}
