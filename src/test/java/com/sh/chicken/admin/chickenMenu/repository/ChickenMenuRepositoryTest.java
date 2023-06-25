package com.sh.chicken.admin.chickenMenu.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ChickenMenuRepositoryTest {

    @Autowired
    ChickenMenuRepository chickenMenuRepository;

    @Test
    public void menuAndLikeFetchJoinTest(){
        // given
        List<ChickenMenu> chickenMenuByFetchJoin = chickenMenuRepository.findChickenMenuByFetchJoin();


        log.info("==========================================================================");
        System.out.println(chickenMenuByFetchJoin.size());
        for (ChickenMenu chickenMenu : chickenMenuByFetchJoin) {
            System.out.println("====== menu ======");
            System.out.println(chickenMenu.getMenuId());
            System.out.println(chickenMenu.getMenuName());
            System.out.println(chickenMenu.getPrice());
            System.out.println(chickenMenu.getContents());
            for (ChickenLike like: chickenMenu.getChickenLikeList()) {
                System.out.println("====== like ======");
                System.out.println(like.getLikeId());
                System.out.println(like.getChickenMenu().getMenuName());
                System.out.println(like.getUsers().getUsername());
            }
        }
        log.info("==========================================================================");
        // when

        // then

    }
}
