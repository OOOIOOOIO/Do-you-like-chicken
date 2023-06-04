package com.sh.chicken.domain.chickenlike.domain.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChickenLikeRepository extends JpaRepository<ChickenLike, Long> {
}
