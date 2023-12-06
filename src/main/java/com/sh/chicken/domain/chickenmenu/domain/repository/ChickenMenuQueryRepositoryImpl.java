package com.sh.chicken.domain.chickenmenu.domain.repository;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.sh.chicken.domain.chickenlike.domain.QChickenLike.*;
import static com.sh.chicken.domain.chickenmenu.domain.QChickenMenu.*;

@Repository
@RequiredArgsConstructor
public class ChickenMenuQueryRepositoryImpl implements ChickenMenuQueryRepository{

    private final JPAQueryFactory queryFactory;

    /**
     * 모든 메뉴 정보 + 총 좋아요 개수, 좋아요개수 순으로 정렬
     */
    @Override
    public List<ChickenMenuInfoResDto> getAllMenusWithTotalLikeLikesDesc(){
        NumberPath<Long> aliasLike = Expressions.numberPath(Long.class, "likes");


        List<ChickenMenuInfoResDto> result = queryFactory
                .select(Projections.constructor(ChickenMenuInfoResDto.class,
                        chickenMenu.menuId,
                        chickenMenu.menuName,
                        chickenMenu.chickenBrand.brandName,
                        chickenMenu.img,
                        chickenMenu.price,
                        chickenMenu.contents,
                        ExpressionUtils.as(
                                JPAExpressions.select(chickenLike.count())
                                .from(chickenLike)
                                        .where(chickenLike.chickenMenu.menuId.eq(chickenMenu.menuId)), aliasLike)
                        )
                )
                .from(chickenMenu)
                .orderBy(aliasLike.desc())
                .fetch();

        return result;
    }

    /**
     * 모든 메뉴 정보 + 총 좋아요 개수, 가격 순으로 정렬
     */
    @Override
    public List<ChickenMenuInfoResDto> getAllMenusWithTotalLikePriceDesc(){
        NumberPath<Long> aliasLike = Expressions.numberPath(Long.class, "likes");


        List<ChickenMenuInfoResDto> result = queryFactory
                .select(Projections.constructor(ChickenMenuInfoResDto.class,
                                chickenMenu.menuId,
                                chickenMenu.menuName,
                                chickenMenu.chickenBrand.brandName,
                                chickenMenu.img,
                                chickenMenu.price,
                                chickenMenu.contents,
                                ExpressionUtils.as(
                                        JPAExpressions.select(chickenLike.count())
                                                .from(chickenLike)
                                                .where(chickenLike.chickenMenu.menuId.eq(chickenMenu.menuId)), aliasLike)
                        )
                )
                .from(chickenMenu)
                .orderBy(chickenMenu.price.desc())
                .fetch();

        return result;
    }


    /**
     * 모든 메뉴 정보, 가격순으로 정렬(좋아요 제외)
     */
    @Override
    public List<ChickenMenuInfoResDto> getAllMenus(){

        List<ChickenMenuInfoResDto> result = queryFactory
                .select(Projections.constructor(ChickenMenuInfoResDto.class,
                        chickenMenu.menuId,
                        chickenMenu.menuName,
                        chickenMenu.chickenBrand.brandName,
                        chickenMenu.img,
                        chickenMenu.price,
                        chickenMenu.contents))
                .from(chickenMenu)
                .orderBy(chickenMenu.price.desc())
                .fetch();


        return result;

    }

    /**
     * 메뉴 상세
     */
    @Override
    public Optional<ChickenMenuInfoResDto> getMenuInfo(Long menuId){
        NumberPath<Long> aliasLike = Expressions.numberPath(Long.class, "likes");

        ChickenMenuInfoResDto chickenMenuInfoResDto = queryFactory
                .select(Projections.constructor(ChickenMenuInfoResDto.class,
                        chickenMenu.menuId,
                        chickenMenu.menuName,
                        chickenMenu.chickenBrand.brandName,
                        chickenMenu.img,
                        chickenMenu.price,
                        chickenMenu.contents,
                        ExpressionUtils.as(
                                JPAExpressions.select(chickenLike.count())
                                        .from(chickenLike)
                                        .where(chickenLike.chickenMenu.menuId.eq(chickenMenu.menuId)), aliasLike)))
                .from(chickenMenu)
                .where(chickenMenu.menuId.eq(menuId))
                .fetchOne();

        return Optional.ofNullable(chickenMenuInfoResDto);
    }

    @Override
    public Long getTotalMenuCount(){
        return queryFactory
                .select(chickenMenu.count())
                .from(chickenMenu)
                .fetchFirst();
    }

}
