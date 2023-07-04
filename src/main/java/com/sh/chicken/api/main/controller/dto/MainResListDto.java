package com.sh.chicken.api.main.controller.dto;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MainResListDto {

    private List<ChickenMenuAndLikesResInterface> chicknMenuList;

    public MainResListDto(List<ChickenMenuAndLikesResInterface> chicknMenuList) {
        this.chicknMenuList = chicknMenuList;
    }
}
