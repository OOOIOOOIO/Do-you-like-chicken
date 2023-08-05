package com.sh.chicken.domain.chickenlike.api;

import com.sh.chicken.domain.chickenlike.application.ChickenLikeService;
import com.sh.chicken.global.aop.log.LogTrace;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSession;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class ChickenLikeController {

    private final ChickenLikeService chickenLikeService;



    @LogTrace
    @PostMapping("/{menuId}")
    public ResponseEntity<String> addLike(@PathVariable("menuId") Long menuId, @UserInfoFromSession UserInfoFromSessionDto userInfoFromSessionDto){
        Long result = chickenLikeService.addLike(menuId, userInfoFromSessionDto.getUserId());

        if (result == 1) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else if (result == 0) {
            throw new RuntimeException("like 중복 저장 시도");
        }

        throw new RuntimeException("like redis 저장 실패");
    }



    @LogTrace
    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteLike(@PathVariable("menuId") Long menuId, @UserInfoFromSession UserInfoFromSessionDto userInfoFromSessionDto){
        Long result = chickenLikeService.deleteLike(menuId, userInfoFromSessionDto.getUserId());

        if (result == 1) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        else if(result == 0){
            throw new RuntimeException("like redis 중복 삭제 시도");
        }
        throw new RuntimeException("like redis 삭제 실패");
    }

}
