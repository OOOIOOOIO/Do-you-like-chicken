package com.sh.chicken.domain.chickenlike.api.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ChickenLikeChangeReqDto {
    private Long menuId;

    @JsonCreator
    public ChickenLikeChangeReqDto(Long menuId, Long userId) {
        this.menuId = menuId;
    }
}
