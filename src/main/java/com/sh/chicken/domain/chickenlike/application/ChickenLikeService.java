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

        return redisUtil.putSet(LIKE.prefix() + menuId, userId, null);
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


}
