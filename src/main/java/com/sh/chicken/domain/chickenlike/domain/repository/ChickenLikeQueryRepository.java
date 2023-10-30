package com.sh.chicken.domain.chickenlike.domain.repository;

import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResDto;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChickenLikeQueryRepository {

    List<ChickenMenuAndLikesResDto> getChickenMenusInfoList(long userId);
}
