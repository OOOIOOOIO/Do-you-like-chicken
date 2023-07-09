package com.sh.chicken.api.mypage.controller.dto.res;

import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPageResDto {

    private UsersInfoResDto userInfo;

    private List<ChickenMenuAndLikesResInterface> userLikeChickenList;

    public MyPageResDto(UsersInfoResDto usersInfoResDto, List<ChickenMenuAndLikesResInterface> chickenMenusInfo) {
        this.userInfo = usersInfoResDto;
        this.userLikeChickenList = chickenMenusInfo;
    }

}
