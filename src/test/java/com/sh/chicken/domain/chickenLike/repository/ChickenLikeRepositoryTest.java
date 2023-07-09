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
import org.springframework.web.server.ServerErrorException;

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
    public void removeByFKTest(){
        // given
        ChickenLike chickenLike = chickenLikeRepository.findByUsers_UserIdAndChickenMenu_MenuId(3L, 2L).orElseThrow(() -> new ServerErrorException("메뉴 없음"));

//        log.info("=="+chickenLike.getLikeId());
//        log.info("=="+chickenLike.getChickenMenu());
//        log.info("=="+chickenLike.getUsers().getUserId());
//        log.info("=="+chickenLike.getUsers().getUsername());
        // when

        // then

    }

    @Test
    public void removeByEntity(){
        // given
        Users users = usersRepository.findById(3L).orElseThrow();
        ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(2L).orElseThrow();

        ChickenLike chickenLike = ChickenLike.createChickenLike(users, chickenMenu);

        chickenLikeRepository.delete(chickenLike);

        // when

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
