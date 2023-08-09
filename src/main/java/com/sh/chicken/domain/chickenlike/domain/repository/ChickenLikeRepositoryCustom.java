package com.sh.chicken.domain.chickenlike.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.sh.chicken.domain.chickenlike.domain.QChickenLike.chickenLike;

@Repository
@RequiredArgsConstructor
public class ChickenLikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<ChickenLike> findByUserIdAndMenuId(Long userId, Long menuId){
        return Optional.ofNullable(queryFactory
                .selectFrom(chickenLike)
                .where(chickenLike.users.userId.eq(userId),
                        chickenLike.chickenMenu.menuId.eq(menuId))
                .fetchOne());
    }

    public List<Long> getLikesByMenuId(Long menuId){

        return queryFactory
                .select(chickenLike.users.userId)
                .from(chickenLike)
                .where(chickenLike.chickenMenu.menuId.eq(menuId))
                .fetch();

    }
}
