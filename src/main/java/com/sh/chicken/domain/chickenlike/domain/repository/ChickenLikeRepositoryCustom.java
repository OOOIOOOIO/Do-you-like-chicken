package com.sh.chicken.domain.chickenlike.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    //벌크 update는 영속성 컨텍스트 비우는게 안전한데
    //벌크 delete는 db에서 조회한 id만 가져오는거라 db에서 삭제된 id를 가진 entity는 알아서 안불러옴
    public Long deleteLikeByUserId(Long menuId, Set<Long> fromRedis){

        return queryFactory.delete(chickenLike)
                .where(chickenLike.chickenMenu.menuId.eq(menuId), chickenLike.users.userId.in(fromRedis))
                .execute();

    }
}
