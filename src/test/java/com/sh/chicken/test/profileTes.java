package com.sh.chicken.test;


import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class profileTes {

    @Autowired
    UsersRepository usersRepository;


    @Test
    public void test(){
        // given
        Users users = usersRepository.findById(8L).get();

        log.info("============users.getUsername()==============");
        log.info(users.getUsername());

        // when

        // then

    }

}
