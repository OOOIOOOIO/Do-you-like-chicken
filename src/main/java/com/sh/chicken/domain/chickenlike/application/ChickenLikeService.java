package com.sh.chicken.domain.chickenlike.application;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.global.common.RedisConst;
import com.sh.chicken.global.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sh.chicken.global.common.RedisConst.LIKE;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChickenLikeService {
    private final RedisUtil redisUtil;

    /**
     * 좋아요 추가
     */
    public Long addLike(Long menuId, Long userId) {
        Long result = redisUtil.putSet(LIKE.prefix() + menuId, userId, null);

        return result;
    }

    /**
     * 좋아요 삭제
     */
    public Long deleteLike(Long menuId, Long userId){

        return redisUtil.removeSetValue(LIKE.prefix() + menuId, String.valueOf(userId));
    }

    public Long totalLike(Long menuId) {

        return redisUtil.getLikeTotalSize(LIKE.prefix() + menuId);

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
