package com.sh.chicken.admin;

import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
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
    ChickenLikeRepositoryCustom chickenLikeRepositoryCustom;

    @Test
    @Transactional
    public void deleteInQuery(){
        // given
        Long menuId = 1L;
        Set<Long> fromRedis = new HashSet<>();
        fromRedis.add(1L);
        fromRedis.add(2L);
        fromRedis.add(3L);
        fromRedis.add(4L);
        // when
        chickenLikeRepositoryCustom.deleteLikeByUserId(menuId, fromRedis);

        // then

    }
}
