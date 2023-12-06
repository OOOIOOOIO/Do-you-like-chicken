package com.sh.chicken.domain.chickenlike.domain.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResDto;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.sh.chicken.domain.chickenlike.domain.QChickenLike.chickenLike;

public interface ChickenLikeQueryRepository {
    Optional<ChickenLike> findByUserIdAndMenuId(Long userId, Long menuId);
    List<Long> getLikesByMenuId(Long menuId);

    Long deleteLikeByUserId(Long menuId, Set<Long> fromRedis);

    List<ChickenMenuAndLikesResDto> getChickenMenusInfoList(long userId);
}
