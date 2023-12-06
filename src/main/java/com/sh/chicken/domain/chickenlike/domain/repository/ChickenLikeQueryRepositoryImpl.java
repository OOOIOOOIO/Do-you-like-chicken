package com.sh.chicken.domain.chickenlike.domain.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;


import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.sh.chicken.domain.chickenlike.domain.QChickenLike.chickenLike;
import static com.sh.chicken.domain.chickenmenu.domain.QChickenMenu.chickenMenu;

@Repository
@RequiredArgsConstructor
public class ChickenLikeQueryRepositoryImpl implements ChickenLikeQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChickenLike> findByUserIdAndMenuId(Long userId, Long menuId){
        return Optional.ofNullable(queryFactory
                .selectFrom(chickenLike)
                .where(chickenLike.users.userId.eq(userId),
                        chickenLike.chickenMenu.menuId.eq(menuId))
                .fetchOne());
    }

    @Override
    public List<Long> getLikesByMenuId(Long menuId){

        return queryFactory
                .select(chickenLike.users.userId)
                .from(chickenLike)
                .where(chickenLike.chickenMenu.menuId.eq(menuId))
                .fetch();

    }

    //벌크 update는 영속성 컨텍스트 비우는게 안전한데
    //벌크 delete는 db에서 조회한 id만 가져오는거라 db에서 삭제된 id를 가진 entity는 알아서 안불러옴
    @Override
    public Long deleteLikeByUserId(Long menuId, Set<Long> fromRedis){

        return queryFactory.delete(chickenLike)
                .where(chickenLike.chickenMenu.menuId.eq(menuId), chickenLike.users.userId.in(fromRedis))
                .execute();

    }

    /**
     * Mypage
     */
    @Override
    public List<ChickenMenuAndLikesResDto> getChickenMenusInfoList(long userId) {
        return queryFactory
                .select(Projections.constructor(
                        ChickenMenuAndLikesResDto.class,
                        chickenMenu.menuId,
                        chickenMenu.menuName,
                        chickenMenu.chickenBrand.brandName,
                        chickenMenu.img,
                        chickenMenu.price,
                        chickenMenu.contents,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(chickenLike.count())
                                        .from(chickenLike)
                                        .where(chickenLike.chickenMenu.menuId.eq(chickenMenu.menuId)),
                                "likes"
                        )
                        )

                )
                .from(chickenLike)
                .innerJoin(chickenMenu).on(chickenLike.chickenMenu.menuId.eq(chickenMenu.menuId))
                .where(chickenLike.users.userId.eq(userId))
                .fetch();
    }
}
