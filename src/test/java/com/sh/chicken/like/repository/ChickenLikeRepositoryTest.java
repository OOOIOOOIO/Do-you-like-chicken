package com.sh.chicken.like.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepositoryCustom;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
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
public class ChickenLikeRepositoryTest {

    @Autowired
    ChickenLikeRepository chickenLikeRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ChickenMenuRepository chickenMenuRepository;




    @Test
    @Transactional
    @DisplayName("chickenLike insert test")
    public void likeBulkInsertTest(){
//        for(int i = 1; i <= 6; i++){
        for(int i = 6001; i <= 8000; i++){
            String username = "test" + i;
            log.info("=== username ===" + username);
            Users user = usersRepository.findByUsername(username).get();

            for(long j = 1; j <= 67; j+=2){
                ChickenMenu chickenMenu = chickenMenuRepository.findById(j).get();
                ChickenLike chickenLike = ChickenLike.createChickenLike(user, chickenMenu);
                chickenLikeRepository.save(chickenLike);
            }
        }

    }


    

}
