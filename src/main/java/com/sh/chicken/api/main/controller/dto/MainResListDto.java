package com.sh.chicken.api.main.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MainResListDto {

    private List<MainResDto> chicknMenuList;

    public MainResListDto(List<MainResDto> mainResDtoList) {
        this.chicknMenuList = mainResDtoList;
    }
}
