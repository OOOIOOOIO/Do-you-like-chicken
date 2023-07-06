package com.sh.chicken.admin.controller;


import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/admin/test")
@RequiredArgsConstructor
public class QuerydslTestController {

    private final ChickenLikeRepositoryCustom chickenLikeRepositoryCustom;

    @Transactional
    @GetMapping("/querydsl")
    public long querydslTest(@RequestParam("userId") long userId, @RequestParam("menuId") long menuId){

        ChickenLike byUserIdAndMenuId = chickenLikeRepositoryCustom.findByUserIdAndMenuId(userId, menuId).orElseThrow(() -> new RuntimeException("ad"));

        return byUserIdAndMenuId.getLikeId();

    }



}
