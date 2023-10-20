package com.sh.chicken.domain.user.application;

import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import com.sh.chicken.global.exception.CustomException;
import com.sh.chicken.global.exception.ErrorCode;
import com.sh.chicken.global.resolver.usersession.UserInfoFromSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

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
    public UserInfoFromSessionDto signIn(UsersSignInReqDto usersSignInDto) {
        Users user = usersRepository.findByUsernameAndPw(usersSignInDto.getUsername(), usersSignInDto.getPw()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        UsersSingInResDto usersSingInResDto = new UsersSingInResDto(user);

        return new UserInfoFromSessionDto(usersSingInResDto);
    }

    /**
     * 회원 조회 - 중복 방지
     */
    public boolean checkDuplicateUsername(String username){

        return usersRepository.findByUsername(username).isPresent();
    }

    public void updateNickname(long userId, String nickname){
        Users users = usersRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        users.changeNickname(nickname);
    }


}
