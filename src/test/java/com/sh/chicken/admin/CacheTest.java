package com.sh.chicken.admin;

import com.sh.chicken.admin.cache.CacheWarmUpService;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeQueryRepositoryImpl;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class CacheTest {
    @Autowired
    ChickenLikeQueryRepositoryImpl chickenLikeRepositoryCustom;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ChickenMenuRepository chickenMenuRepository;
    @Autowired
    ChickenLikeRepository chickenLikeRepository;

    @Autowired
    CacheWarmUpService cacheWarmUpService;

//    @Test
    @Disabled
    @Transactional
    public void deleteInQuery(){
        // given
        Long menuId = 1L;

        Set<Long> userFromRedis = userFromRedis();
        // when
        chickenLikeRepositoryCustom.deleteLikeByUserId(menuId, userFromRedis);

        // then

    }


    @Test
    public void matchConsistencyTest(){
        // given
        cacheWarmUpService.matchConsistency();

        // when
        cacheWarmUpService.pushChickenMenuLikeBulkInsert(); //맞춘 후 redis에 밀어넣기

        // then

    }


    private static Set<Long> userFromRedis() {
        Set<Long> userFromRedis = new HashSet<>();
        userFromRedis.add(3L);
        userFromRedis.add(4L);
        userFromRedis.add(5L);
        userFromRedis.add(6L);
        return userFromRedis;
    }
}
