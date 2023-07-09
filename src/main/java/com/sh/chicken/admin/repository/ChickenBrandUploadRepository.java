package com.sh.chicken.admin.repository;

import com.sh.chicken.domain.chickenbrand.domain.ChickenBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickenBrandUploadRepository extends JpaRepository<ChickenBrand, Long> {

    Optional<ChickenBrand> findByBrandName(@Param("brandName") String brandName);


}
