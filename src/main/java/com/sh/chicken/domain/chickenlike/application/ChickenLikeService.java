package com.sh.chicken.domain.chickenlike.application;

import com.sh.chicken.api.main.controller.dto.MainResListDto;
import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
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
        log.info("==================================");
        Users users = usersRepository.findById(userId).orElseThrow(() -> new ServerErrorException("유저 없음"));
        ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(menuId).orElseThrow(() -> new ServerErrorException("메뉴 없음"));

        ChickenLike chickenLike = ChickenLike.createChickenLike(users, chickenMenu);

        if(!isPresent(users, chickenMenu)){
            chickenLikeRepository.save(chickenLike);

        }else{
            throw new RuntimeException("이미 좋아요 누름");
        }



//        chickenLike.saveChickenLike(users, chickenMenu);

    }

    public void delete(Long menuId, Long userId){
        log.info("================delete==================");
        // 이거 left join 2번 나옴
        // querydsl로 바꾸기 / 흠 엔티티 때문에... 흠
//        ChickenLike chickenLike = chickenLikeRepository.findByUsers_UserIdAndChickenMenu_MenuId(menuId, userId).orElseThrow(() -> new ServerErrorException("메뉴 없음"));
        Users users = usersRepository.findById(userId).orElseThrow(() -> new ServerErrorException("유저 없음"));
        ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(menuId).orElseThrow(() -> new ServerErrorException("메뉴 없음"));

        ChickenLike chickenLike = chickenLikeRepository.findByUsersAndChickenMenu(users, chickenMenu).orElseThrow(() -> new ServerErrorException("유저 없음"));

        chickenLikeRepository.delete(chickenLike);

    }

    public MainResListDto getChickenMenusOrderByLikesDesc(){
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.getAllChickenMenusWithLikeOrderByLikesDESC();

        return new MainResListDto(chickenMenuBySelectSubQuery);
    }

    private boolean isPresent(Users users, ChickenMenu chickenMenu) {

        Optional<ChickenLike> byUsersAndChickenMenu = chickenLikeRepository.findByUsersAndChickenMenu(users, chickenMenu);

        if(byUsersAndChickenMenu.isPresent()) return true;

        return false;

    }

}
