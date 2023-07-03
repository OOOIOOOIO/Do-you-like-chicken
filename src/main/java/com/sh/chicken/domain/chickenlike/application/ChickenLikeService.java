package com.sh.chicken.domain.chickenlike.application;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChickenLikeService {

    private final ChickenLikeRepository chickenLikeRepository;
    private final UsersRepository usersRepository;
    private final ChickenMenuRepository chickenMenuRepository;
    public void add(Long menuId, Long userId) {

        Users users = usersRepository.findById(userId).orElseThrow(() -> new ServerErrorException("유저 없음"));
        ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(menuId).orElseThrow(() -> new ServerErrorException("메뉴 없음"));

        ChickenLike chickenLike = ChickenLike.createChickenLike(users, chickenMenu);

        chickenLikeRepository.save(chickenLike);

    }

    public void remove(Long menuId, Long userId){
        ChickenLike chickenLike = chickenLikeRepository.findByUsers_UserIdAndChickenMenu_MenuId(menuId, userId).orElseThrow(() -> new ServerErrorException("메뉴 없음"));

        chickenLikeRepository.delete(chickenLike);

    }


}
