package com.sh.chicken.api.common.dto;

import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChickenMenusAndTotalLikeResListDto {

    private List<ChickenMenuAndLikesResInterface> chicknMenuList;

    public ChickenMenusAndTotalLikeResListDto(List<ChickenMenuAndLikesResInterface> chicknMenuList) {
        this.chicknMenuList = chicknMenuList;
    }
}
