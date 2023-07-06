package com.sh.chicken.api.mypage.service;


import com.sh.chicken.api.mypage.controller.dto.res.MyPageResDto;
import com.sh.chicken.api.mypage.controller.dto.res.UsersInfoResDto;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final UsersRepository usersRepository;
    private final ChickenLikeRepository chickenLikeRepository;


    public void updateNickname(long userId, String nickname){
        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없음"));

        users.changeNickname(nickname);
    }

    // 일 대 다 해보자

    public MyPageResDto getMyChickenMenuLikes(long userId){

        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없음"));

        UsersInfoResDto usersInfoResDto = new UsersInfoResDto(users);
        List<ChickenMenuAndLikesResInterface> chickenMenusInfo = chickenLikeRepository.getChickenMenusInfo(userId);


        return new MyPageResDto(usersInfoResDto, chickenMenusInfo);
    }

}
