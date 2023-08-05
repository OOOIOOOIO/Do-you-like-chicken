package com.sh.chicken.admin.test;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final UsersRepository usersRepository;

    @Transactional
    public UserLikeDto getThat(){
        Users users = usersRepository.findChickenLikes(4L).get();
//        Users users = usersRepository.findChickenLikes(4L).get();

        UserLikeDto userLikeDto = new UserLikeDto(users);

        return userLikeDto;
    }


}
