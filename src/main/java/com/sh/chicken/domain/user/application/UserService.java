package com.sh.chicken.domain.user.application;

import com.sh.chicken.api.jwt.controller.dto.AccessTokenResponseDto;
import com.sh.chicken.domain.chickenlike.application.ChickenLikeService;
import com.sh.chicken.domain.user.api.dto.request.UsersSignInReqDto;
import com.sh.chicken.domain.user.api.dto.request.UsersSignUpReqDto;
import com.sh.chicken.domain.user.api.dto.response.UsersSingInResDto;
import com.sh.chicken.domain.user.domain.ERole;
import com.sh.chicken.domain.user.domain.Role;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.RoleRepository;
import com.sh.chicken.domain.user.domain.repository.UserRepository;
import com.sh.chicken.global.jwt.JwtClaimDto;
import com.sh.chicken.global.jwt.JwtInfoProperties;
import com.sh.chicken.global.jwt.JwtUtils;
import com.sh.chicken.global.exception.CustomException;
import com.sh.chicken.global.exception.CustomErrorCode;
import com.sh.chicken.global.util.redis.RedisUtil;
import com.sh.chicken.global.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sh.chicken.global.common.RedisConst.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository usersRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final RedisUtil redisUtil;
    private final JwtInfoProperties jwtInfoProperties;


    /**
     * 회원가입
     * role 설정
     */
    public void signUp(UsersSignUpReqDto usersSignUpDto) {

        if (isDuplicateUsername(usersSignUpDto.getUsername())) {
            throw new CustomException(CustomErrorCode.DuplicateUsernameExcepetion);
        }

        // 유저 생성
        Users user = Users.createUser(usersSignUpDto, passwordEncoder.encode(usersSignUpDto.getPw()));

        Set<String> strRoles = usersSignUpDto.getRole();

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("ERROR : ROLE IS NOT FOUND"));

            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        usersRepository.save(user);
    }

    /**
     * 로그인 - 조회
     * 0. id+pw 검사
     * 1. securityContext 인증
     * 2. accessToken 발급
     * 3. refreshToken 발급
     * 4. 끝
     *
     * 근데 security context는 Thread 마다인데... singIn에 하는 건 좋은데 각각에 해야하낭... 흠흠 의미 없나.... 나중에 찾아보자
     */
    public UsersSingInResDto signIn(UsersSignInReqDto usersSignInDto) {

        // 검사
        Users user = usersRepository.findByUsernameAndPw(usersSignInDto.getUsername(), usersSignInDto.getPw())
                .orElseThrow(() -> new CustomException(CustomErrorCode.UserNotFoundException));


        // 인증
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPw()));

        // SecurityContext에 올림
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 유저 정보 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // 마지막 응답할때 role
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // accessToken 발급
        String accessToken = jwtUtils.generateAccessToken(user.getUserId(), user.getUsername());

        // refreshToken 발급
        String refreshToken = jwtUtils.generateRefreshToken(user.getUserId(), user.getUsername());

        /**
         * redis에 넣기
         */

        redisUtil.putString(ACCESS_TOKEN.prefix() + user.getUserId(), accessToken, jwtInfoProperties.getAccessTokenExpireMin());
        log.info("PUT ACCESS_TOKEN INTO REDIS");

        redisUtil.putString(REFRESH_TOKEN.prefix() + user.getUserId(), refreshToken, jwtInfoProperties.getRefreshTokenExpireMin());
        log.info("PUT REFRESH_TOKEN INTO REDIS");

        UsersSingInResDto usersSingInResDto = new UsersSingInResDto(user, accessToken, refreshToken);

        return usersSingInResDto;
    }

    /**
     * accesstoken 재발급
     */
    public AccessTokenResponseDto reIssueAccessToken(HttpServletRequest request){

        String refreshTokenFromHeader = jwtUtils.getRefreshTokenFromHeader(request);

        JwtClaimDto claimFromToken = jwtUtils.getClaimFromToken(refreshTokenFromHeader);

        String accessToken = jwtUtils.generateAccessToken(claimFromToken.getUserId(), claimFromToken.getUsername());

        return new AccessTokenResponseDto(accessToken);
    }


    /**
     * 회원 조회 - 중복 방지
     */
    private boolean isDuplicateUsername(String username){

        return usersRepository.findByUsername(username).isPresent();
    }

    public void updateNickname(long userId, String nickname){
        Users users = usersRepository.findById(userId).orElseThrow(() -> new CustomException(CustomErrorCode.UserNotFoundException));

        users.changeNickname(nickname);
    }


}

/*

user id -> menuid -> menu
like id
menu id
user id
 */