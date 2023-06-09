package com.sh.chicken.domain.user.application;

import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsersApiService {

    private final UsersRepository usersRepository;

    /**
     * 회원가입 - 저장
     */
    public void signUp(UsersSignUpReqDto usersSignUpDto) {

        Users user = Users.createUser(usersSignUpDto);

        usersRepository.save(user);
    }

    /**
     * 로그인 - 조회
     */
    public UsersSingInResDto signIn(UsersSignInReqDto usersSignInDto) {

        Users user = usersRepository.findByUsernameAndPw(usersSignInDto.getUsername(), usersSignInDto.getPw())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));


        return new UsersSingInResDto(user);
    }

    /**
     * 회원 조회 - 중복 방지
     */
    public boolean checkDuplicateUsername(String username){

        return usersRepository.findByUsername(username).isPresent();
    }
}
