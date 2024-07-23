package com.sh.chicken.domain.chickenlike.domain.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChickenLikeQueryRepository {
    Optional<ChickenLike> findLikeByUserIdAndMenuId(Long userId, Long menuId);
    List<Long> getLikesByMenuId(Long menuId);

    Long deleteLikeByUserId(Long menuId, Set<Long> fromRedis);

    List<ChickenMenuAndLikesResDto> getChickenMenusInfoList(long userId);
}
