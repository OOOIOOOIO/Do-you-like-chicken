package com.sh.chicken.domain.chickenlike.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;

import static com.sh.chicken.domain.chickenlike.domain.QChickenLike.chickenLike;
import static org.springframework.data.jpa.domain.Specification.where;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChickenLikeRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ChickenLikeRepositorySupport(JPAQueryFactory queryFactory) {
        super(ChickenLike.class);
        this.queryFactory = queryFactory;
    }

    public Optional<ChickenLike> findByUserIdAndMenuId(Long userId, Long menuId){
        return Optional.ofNullable(queryFactory
                .selectFrom(chickenLike)
                .where(chickenLike.users.userId.eq(userId),
                        chickenLike.chickenMenu.menuId.eq(menuId))

                .fetchOne());



    }
}
