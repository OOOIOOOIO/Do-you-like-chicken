package com.sh.chicken.domain.chickenmenu.domain.repository;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChickenMenuRepository extends JpaRepository<ChickenMenu, Long> {

    List<ChickenMenu> findAllByImgIsNotNullOrderByPriceDesc();

    Optional<ChickenMenu> findByMenuId(@Param("menuId") long menuId);


    @Query("select distinct cm from ChickenMenu cm join fetch cm.chickenLikeList")
    List<ChickenMenu> findChickenMenuByFetchJoin();

//    List<ChickenMenuAndLikesDto> findChickenMenuByFetchJoin();

}

/**
 -- 이거
 SELECT *, (select count(*) from chicken_like cl where cl.menu_id = cm.menu_id)
 from chicken_menu cm;


 -- 이거
 SELECT cm.*, cl.likes
 from chicken_menu as cm left join (
 select menu_id, count(*) as likes
 from chicken_like
 group by menu_id
 ) as cl
 on cm.menu_id = cl.menu_id;

 */