package com.sh.chicken.domain.chickenLike.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepositoryCustom;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
public class ChickenLikeRepositoryTest {
    @Autowired
    ChickenLikeRepository chickenLikeRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ChickenMenuRepository chickenMenuRepository;
    @Autowired
    ChickenLikeRepositoryCustom chickenLikeRepositorySupport;

    @Test
    public void removeByEntity(){
        // given
        Users users = usersRepository.findById(3L).orElseThrow();
        ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(2L).orElseThrow();

        ChickenLike chickenLike = ChickenLike.createChickenLike(users, chickenMenu);

        // when
        chickenLikeRepository.delete(chickenLike);

        // then

    }

    @Test
    public void querydslSelectTest(){
        // given
        ChickenLike chickenLike = chickenLikeRepositorySupport.findByUserIdAndMenuId(3L, 2L).orElseThrow();
        // when
        log.info("===========" + chickenLike.getLikeId());
        // then

    }

}
