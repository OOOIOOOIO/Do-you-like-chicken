package com.sh.chicken.domain.chickenlike.domain.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickenLikeRepository extends JpaRepository<ChickenLike, Long> {

    public Optional<ChickenLike> findByMenuIdAndUserId(@Param("menuId") Long menuId, @Param("userId") Long userId);
    public Optional<ChickenLike> findByChickenMenuAndUsers(@Param("menuId") Long menuId, @Param("userId") Long userId);
}
