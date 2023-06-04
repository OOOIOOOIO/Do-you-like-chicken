package com.sh.chicken.admin.repository;

import com.sh.chicken.domain.chickenbrand.domain.ChickenBrand;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickenMenuUploadRepository extends JpaRepository<ChickenMenu, Long> {

    public Optional<ChickenMenu> findByMenuNameAndChickenBrand(@Param("menuName") String menuName, @Param("chickenBrand") ChickenBrand brandName);
}
