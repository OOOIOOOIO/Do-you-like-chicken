package com.sh.chicken.domain.chickenlike.api;

import com.sh.chicken.domain.chickenlike.application.ChickenLikeService;
import com.sh.chicken.global.aop.log.LogTrace;
import com.sh.chicken.global.exception.CustomException;
import com.sh.chicken.global.exception.CustomErrorCode;
import com.sh.chicken.global.resolver.UserInfoFromHeader;
import com.sh.chicken.global.resolver.UserInfoFromHeaderDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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



    @ApiOperation(value = "치킨 메뉴 좋아요 추가 api", notes = "치킨 메뉴 좋아요를 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "저장 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @LogTrace
    @PostMapping("/{menuId}")
    public ResponseEntity<String> addLike(@PathVariable("menuId") Long menuId, @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        Long result = chickenLikeService.addLike(menuId, userInfoFromHeaderDto.getUserId());

        if (result == 1) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else if (result == 0) {
            throw new CustomException(CustomErrorCode.DuplicateSaveAttemptedException);
        }

        throw new CustomException(CustomErrorCode.FailToSaveFileInRedisException);
    }


    @ApiOperation(value = "치킨 메뉴 좋아요 삭제 api", notes = "치" +
            "킨 메뉴 좋아요를 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @LogTrace
    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteLike(@PathVariable("menuId") Long menuId, @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        Long result = chickenLikeService.deleteLike(menuId, userInfoFromHeaderDto.getUserId());

        if (result == 1) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        else if(result == 0){
            throw new CustomException(CustomErrorCode.AlreadyDeletedException);
        }
        throw new CustomException(CustomErrorCode.FailToDeleteFIleInRedisException);
    }

}
