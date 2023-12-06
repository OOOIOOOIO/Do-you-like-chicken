package com.sh.chicken.admin.test;

import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final UserRepository usersRepository;

    @Transactional
    public UserLikeDto getThat(){
        Users users = usersRepository.findChickenLikes(4L).get();
//        Users users = usersRepository.findChickenLikes(4L).get();

        UserLikeDto userLikeDto = new UserLikeDto(users);

        return userLikeDto;
    }


}
