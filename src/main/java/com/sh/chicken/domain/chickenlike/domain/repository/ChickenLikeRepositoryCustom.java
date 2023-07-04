package com.sh.chicken.domain.chickenlike.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;

import static com.sh.chicken.domain.chickenlike.domain.QChickenLike.chickenLike;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChickenLikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * menuId, userId로 like 하나 조회
     */
    public Optional<ChickenLike> findByUserIdAndMenuId(Long userId, Long menuId){




        return Optional.ofNullable(queryFactory
                .selectFrom(chickenLike)
                .where(chickenLike.users.userId.eq(userId),
                        chickenLike.chickenMenu.menuId.eq(menuId))

                .fetchOne());



    }
}
