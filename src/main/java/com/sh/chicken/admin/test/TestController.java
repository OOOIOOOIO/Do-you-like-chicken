package com.sh.chicken.admin.test;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("")
    public ResponseEntity<UserLikeDto> updateNickname() {
        UserLikeDto userLikeDto = testService.getThat();

        return new ResponseEntity<>(userLikeDto, HttpStatus.OK);
    }

}
