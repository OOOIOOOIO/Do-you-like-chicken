package com.sh.chicken.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChickenMenusAndTotalLikeResListDto {

    private List<ChickenMenuAndLikesResInterface> chicknMenuList;

    public ChickenMenusAndTotalLikeResListDto(List<ChickenMenuAndLikesResInterface> chicknMenuList) {
        this.chicknMenuList = chicknMenuList;
    }
}
