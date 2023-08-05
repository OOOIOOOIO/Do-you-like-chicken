package com.sh.chicken.users.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@Slf4j
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @Test
    @Transactional
    public void userBulkInsertTest(){

        for(int i = 5001; i <= 10000; i++){
            String username = "test" + i;
            String pw = "test" + i;;
            String nickname= "test" + i;;
            int sex = i % 2;
            UsersSignUpReqDto usersSignUpReqDto = new UsersSignUpReqDto(username, pw, nickname, sex);
            usersRepository.save(Users.createUser(usersSignUpReqDto));
        }
    }
    
    @Test
    public void getChickenLike(){
        // given
        Long userId = 4L;
        String nickName = "test3";
        // when
        Users users = usersRepository.findChickenLikes(userId).get();

        // then
        assertThat(users.getChickenLikeList().size()).isEqualTo(67);
        assertThat(users.getNickname()).isEqualTo(nickName);

        for(ChickenLike chickenLike : users.getChickenLikeList()){
            log.info(chickenLike.getLikeId()+"");
            log.info("====================================");

        }


    }
}
