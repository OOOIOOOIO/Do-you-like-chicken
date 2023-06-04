package com.sh.chicken.domain.chickenmenu.domain.repository;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChickenMenuRepository extends JpaRepository<ChickenMenu, Long> {

    List<ChickenMenu> findAllByImgIsNotNullOrderByPriceDesc();

    Optional<ChickenMenu> findByMenuId(@Param("menuId") long menuId);

}
