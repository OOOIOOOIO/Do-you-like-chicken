package com.sh.chicken.domain.chickenlike.api;

import com.sh.chicken.api.common.dto.ChickenMenusAndTotalLikeResListDto;
import com.sh.chicken.domain.chickenlike.application.ChickenLikeService;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.global.SessionConst;
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
    public ResponseEntity<String> add(@PathVariable("menuId") Long menuId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UsersSingInResDto userInfo = (UsersSingInResDto)session.getAttribute(SessionConst.COMMON_USER.getRule());

        chickenLikeApiService.add(menuId, userInfo.getUserId());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> delete(@PathVariable("menuId") Long menuId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UsersSingInResDto userInfo = (UsersSingInResDto)session.getAttribute(SessionConst.COMMON_USER.getRule());

        chickenLikeApiService.delete(menuId, userInfo.getUserId());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<ChickenMenusAndTotalLikeResListDto> sortByLike(){

        ChickenMenusAndTotalLikeResListDto chickenMenuList = chickenLikeApiService.getChickenMenusOrderByLikesDesc();

        return new ResponseEntity<>(chickenMenuList, HttpStatus.OK);
    }


}
