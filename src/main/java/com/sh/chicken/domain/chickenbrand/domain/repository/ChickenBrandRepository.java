package com.sh.chicken.domain.chickenbrand.domain.repository;

import com.sh.chicken.domain.chickenbrand.domain.ChickenBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickenBrandRepository extends JpaRepository<ChickenBrand, Long> {

    Optional<ChickenBrand> findByBrandName(@Param("brandName") String brandName);


}
