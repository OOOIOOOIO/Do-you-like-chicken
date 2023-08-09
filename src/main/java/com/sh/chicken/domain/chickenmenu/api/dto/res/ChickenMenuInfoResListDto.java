package com.sh.chicken.domain.chickenmenu.api.dto.res;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChickenMenuInfoResListDto {

    private List<ChickenMenuInfoResDto> chickenMenuInfoResDto;

    public ChickenMenuInfoResListDto(List<ChickenMenuInfoResDto> chickenMenuInfoResDto) {
        this.chickenMenuInfoResDto = chickenMenuInfoResDto;
    }
}
