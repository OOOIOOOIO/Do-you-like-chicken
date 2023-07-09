package com.sh.chicken.domain.chickenmenu.domain.repository;

import com.sh.chicken.domain.chickenbrand.domain.ChickenBrand;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChickenMenuRepository extends JpaRepository<ChickenMenu, Long> {

    Optional<ChickenMenu> findByMenuId(@Param("menuId") long menuId);

    public Optional<ChickenMenu> findByMenuNameAndChickenBrand(@Param("menuName") String menuName, @Param("chickenBrand") ChickenBrand brandName);


    @Query(value = "SELECT cm.menu_id as menuId, cm.menu_name as menuName, cm.brand_name as brandName, cm.img, cm.price, cm.contents, " +
            "(select count(*) from chicken_like cl where cl.menu_id = cm.menu_id) as likes" +
            " from chicken_menu cm" +
            " where cm.menu_id = :menuId", nativeQuery = true)
    Optional<ChickenMenuAndLikesResInterface> findMenuAndLikesByMenuId(@Param("menuId") long menuId);


    @Query(value = "SELECT cm.menu_id as menuId, cm.menu_name as menuName, cm.brand_name as brandName, cm.img, cm.price, cm.contents, " +
            "(select count(*) from chicken_like cl where cl.menu_id = cm.menu_id) as likes" +
            " from chicken_menu cm", nativeQuery = true)
    List<ChickenMenuAndLikesResInterface> getAllChickenMenusWithLike();



    @Query(value = "SELECT cm.menu_id as menuId, cm.menu_name as menuName, cm.brand_name as brandName, cm.img, cm.price, cm.contents, " +
            "(select count(*) from chicken_like cl where cl.menu_id = cm.menu_id) as likes" +
            " from chicken_menu cm" +
            " order by likes desc", nativeQuery = true)
    List<ChickenMenuAndLikesResInterface> getAllChickenMenusWithLikeOrderByLikesDESC();








}

