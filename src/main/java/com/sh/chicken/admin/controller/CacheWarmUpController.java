package com.sh.chicken.admin.controller;


import com.sh.chicken.admin.application.CacheWarmUpService;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/warmup")
public class CacheWarmUpController {

    private final CacheWarmUpService cacheWarmUpService;

    /**
     * warm up 항목
     * - 전체 menu list
     * - 개별 menu info
     * - 각 치킨 별 좋아요한 사람
     */

    @LogTrace
    @GetMapping("/likes")
    public void pushLikes(){

        cacheWarmUpService.pushAllChickenMenus();

        cacheWarmUpService.pushChickenMenuLike();

        cacheWarmUpService.pushChickenMenuInfo();

    }
}
