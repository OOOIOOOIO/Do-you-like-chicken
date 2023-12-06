package com.sh.chicken.like.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;


@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class ChickenLikeRepositoryTest {

    @Autowired
    ChickenLikeRepository chickenLikeRepository;
    @Autowired
    UserRepository usersRepository;
    @Autowired
    ChickenMenuRepository chickenMenuRepository;



    @Test
    @Transactional
    @DisplayName("chickenLike insert test")
    public void likeBulkInsertTest(){
        Random random = new Random();
        for(int i = 1; i <= 2000; i++){
            String username = "test" + i;
            long menuId = (long)(random.nextInt(66) + 1);
            log.info("=== username ===" + username);

            Users user = usersRepository.findByUsername(username).get();
            ChickenMenu chickenMenu = chickenMenuRepository.findById(menuId).get();
            ChickenLike chickenLike = ChickenLike.createChickenLike(user, chickenMenu);

            chickenLikeRepository.save(chickenLike);

        }

    }





    

}
