package com.sh.chicken.admin.chickenLike.service;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class ChickenLikeServiceTest {

    @Autowired
    ChickenLikeRepository chickenLikeRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ChickenMenuRepository chickenMenuRepository;

    @Test
    public void bulkInsertTest(){
        // given

        for(int i = 1; i <= 4; i++){
            String username = "test" + i;
            log.info("=== username ===" + username);
            Users user = usersRepository.findByUsername(username).get();

            for(long j = 1; j <= 67; j++){
                ChickenMenu chickenMenu = chickenMenuRepository.findById(j).get();
                ChickenLike chickenLike = ChickenLike.createChickenLike(user, chickenMenu);
                chickenLikeRepository.save(chickenLike);
            }
        }

        // when

        // then

    }


}
