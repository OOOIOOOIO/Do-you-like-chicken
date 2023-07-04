package com.sh.chicken.domain.chickenmenu.domain.repository;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChickenMenuRepository extends JpaRepository<ChickenMenu, Long> {

    Optional<ChickenMenu> findByMenuId(@Param("menuId") long menuId);

    // 이거 쓸거임
    @Query(value = "SELECT cm.menu_id as menuId, cm.menu_name as menuName, cm.brand_name as brandName, cm.img, cm.price, cm.contents, " +
            "(select count(*) from chicken_like cl where cl.menu_id = cm.menu_id) as likes" +
            " from chicken_menu cm", nativeQuery = true)
    List<ChickenMenuAndLikesResInterface> getAllChickenMenusWithLike();

    @Query(value = "SELECT cm.menu_id as menuId, cm.menu_name as menuName, cm.brand_name as brandName, cm.img, cm.price, cm.contents, " +
            "(select count(*) from chicken_like cl where cl.menu_id = cm.menu_id) as likes" +
            " from chicken_menu cm" +
            " order by likes desc", nativeQuery = true)
    List<ChickenMenuAndLikesResInterface> getAllChickenMenusWithLikeOrderByLikesDESC();


    @Query("select distinct cm from ChickenMenu cm " +
            "join fetch cm.chickenBrand " +
            "join fetch cm.chickenLikeList")
    List<ChickenMenu> findChickenMenuByFetchJoin();



    @Query(value = " SELECT cm.*, cl.likes" +
            " from chicken_menu as cm left join" +
            " (select menu_id, count(*) as likes" +
            " from chicken_like" +
            " group by menu_id" +
            " ) as cl" +
            " on cm.menu_id = cl.menu_id;", nativeQuery = true)
    List<ChickenMenuAndLikesResInterface> findChickenMenuByFromSubQuery();



}

