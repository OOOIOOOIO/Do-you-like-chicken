package com.sh.chicken.api.mypage.service;


import com.sh.chicken.api.mypage.controller.dto.res.MyPageResDto;
import com.sh.chicken.api.mypage.controller.dto.res.UsersInfoResDto;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeQueryRepository;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResDto;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UserRepository;
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

    private final UserRepository usersRepository;
    private final ChickenLikeQueryRepository chickenLikeQueryRepository;



    public void updateNickname(long userId, String nickname){
        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없음"));

        users.changeNickname(nickname);
    }


    public MyPageResDto getMyChickenMenuLikes(long userId){
        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없음"));

        UsersInfoResDto usersInfoResDto = new UsersInfoResDto(users);
        List<ChickenMenuAndLikesResDto> chickenMenusInfoList = chickenLikeQueryRepository.getChickenMenusInfoList(userId);

        return new MyPageResDto(usersInfoResDto, chickenMenusInfoList);
    }

}

