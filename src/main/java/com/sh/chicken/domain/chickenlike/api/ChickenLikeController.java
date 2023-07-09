package com.sh.chicken.domain.chickenlike.api;

import com.sh.chicken.api.common.dto.ChickenMenusAndTotalLikeResListDto;
import com.sh.chicken.domain.chickenlike.application.ChickenLikeService;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.global.common.SessionConst;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSession;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class ChickenLikeController {

    private final ChickenLikeService chickenLikeApiService;


    @PostMapping("/{menuId}")
    public ResponseEntity<String> add(@PathVariable("menuId") Long menuId, @UserInfoFromSession UserInfoFromSessionDto userInfoFromSessionDto){
        chickenLikeApiService.add(menuId, userInfoFromSessionDto.getUserId());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> delete(@PathVariable("menuId") Long menuId, @UserInfoFromSession UserInfoFromSessionDto userInfoFromSessionDto){
        chickenLikeApiService.delete(menuId, userInfoFromSessionDto.getUserId());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<ChickenMenusAndTotalLikeResListDto> sortByLike(){
        ChickenMenusAndTotalLikeResListDto chickenMenuList = chickenLikeApiService.getChickenMenusOrderByLikesDesc();

        return new ResponseEntity<>(chickenMenuList, HttpStatus.OK);
    }


}
