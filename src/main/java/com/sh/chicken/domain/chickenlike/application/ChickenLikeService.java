package com.sh.chicken.domain.chickenlike.application;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.global.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChickenLikeService {
    private final RedisUtil redisUtil;

    private static final String KEY = "MENU:LIKE:";

    public Long addLike(Long menuId, Long userId) {

        // redis 잘 쐈다
        Long result = redisUtil.putSet(KEY + menuId, userId, null);

        return result;
    }

    public Long deleteLike(Long menuId, Long userId){

        return redisUtil.removeSetValue(KEY + menuId, String.valueOf(userId));
    }

    public Long totalLike(Long menuId) {

        return redisUtil.totalLike(KEY + menuId);

    }

    private void isPresent(Users users, ChickenMenu chickenMenu) {

//        Optional<ChickenLike> byUsersAndChickenMenu = chickenLikeRepository.findByUsersAndChickenMenu(users, chickenMenu);
//
//        if(byUsersAndChickenMenu.isPresent()) return true;
//
//        return false;
    }



    public void insertToDB(){
        //        Users users = usersRepository.findById(userId).orElseThrow(() -> new ServerErrorException("유저 없음"));
//        ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(menuId).orElseThrow(() -> new ServerErrorException("메뉴 없음"));
//
//        ChickenLike chickenLike = ChickenLike.createChickenLike(users, chickenMenu);
//
//        if(!isPresent(users, chickenMenu)){
//            chickenLikeRepository.save(chickenLike);
//
//        }else{
//            throw new RuntimeException("이미 좋아요 누름");
//        }
    }

    public void deleteFromDB(){
        //        Users users = usersRepository.findById(userId).orElseThrow(() -> new ServerErrorException("유저 없음"));
//        ChickenMenu chickenMenu = chickenMenuRepository.findByMenuId(menuId).orElseThrow(() -> new ServerErrorException("메뉴 없음"));
//
//        ChickenLike chickenLike = chickenLikeRepository.findByUsersAndChickenMenu(users, chickenMenu).orElseThrow(() -> new ServerErrorException("유저 없음"));
//
//        chickenLikeRepository.delete(chickenLike);
    }

}
