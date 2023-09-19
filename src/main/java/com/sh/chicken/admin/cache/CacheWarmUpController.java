package com.sh.chicken.admin.cache;


import com.sh.chicken.admin.cache.CacheWarmUpService;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
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
    @GetMapping("/main")
    public void pushMenusWithString() {

        log.info("push main to redis");
        cacheWarmUpService.pushAllChickenMenusBulkInsert();

    }

    @LogTrace
    @GetMapping("/menus")
    public void pushMenusWithBulk() {

        log.info("push menus to redis");
        cacheWarmUpService.pushChickenMenuInfo();

    }

    @LogTrace
    @GetMapping("/consistency")
    @Scheduled(cron = "0 0 0 * * *") // 매일 정각
    public void matchConsistencyAndPushLikes(){
        log.info("match consistency cache and db");
        cacheWarmUpService.matchConsistency(); // 정합성 맞추기

        log.info("push likes to cache from db");
        cacheWarmUpService.pushChickenMenuLikeBulkInsert(); //맞춘 후 redis에 밀어넣기
    }
}
