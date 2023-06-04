package com.sh.chicken.web.main.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MainResListDto {

    private List<MainResDto> mainResDtoList;

    public MainResListDto(List<MainResDto> mainResDtoList) {
        this.mainResDtoList = mainResDtoList;
    }
}
