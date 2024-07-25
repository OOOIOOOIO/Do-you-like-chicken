package com.sh.chicken.global.jwt;

import com.sh.chicken.global.common.RedisConst;
import com.sh.chicken.global.exception.JwtCustomErrorCode;
import com.sh.chicken.global.exception.JwtCustomException;
import com.sh.chicken.global.util.redis.RedisUtil;
import com.sh.chicken.global.util.security.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@Slf4j
@RequiredArgsConstructor
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final RedisUtil redisUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final static String ACCESS_TOKEN_HEADER_NAME = "access_token";
    private final static String REFRESH_TOKEN_HEADER_NAME = "refresh_token";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("======= Request Path =========");
        String requestURI = request.getRequestURI();
        log.info("======= " + requestURI+" =======");
        checkToken(request);


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

//        String[] excludePath = {"/api/token/**"};
        String[] excludePath = {
                "/api/users/signup",
                "/api/users/signin",
                "/api/issue/access-token",
                "/admin/warmup/main",
                "/admin/warmup/menus",
                "/admin/warmup/consistency",
                "/admin/excel/upload",
                "/admin/excel/brand/upload",
                "/admin/excel/menu/upload",
                "/admin/excel/menu/upload/img",
                "/favicon.ico",
                "/swagger-ui/",
                "/swagger-resources",
                "/swagger-ui/index.html",
                "/swagger-ui/favicon-16x16.png",
                "/swagger-ui/favicon-32x32.png",
                "/swagger-ui/swagger-initializer.js",
                "/swagger-ui/swagger-ui-standalone-preset.js",
                "/swagger-ui/swagger-ui-bundle.js",
                "/swagger-ui/index.css",
                "/swagger-ui/swagger-ui.css",
                "/api-docs/swagger-config",
                "/v3/api-docs",
                "/api-docs/CHICKEN%20API",
                "/error"


        };
        String path = request.getRequestURI();

        return Arrays.stream(excludePath).anyMatch(path::startsWith);

    }

    private void checkToken(HttpServletRequest request) {
        checkAccessToken(request);
    }


    private boolean checkAccessToken(HttpServletRequest request){

        //accessToken 검사
        try{
            String accessToken = request.getHeader(ACCESS_TOKEN_HEADER_NAME);

            checkNull(accessToken);

            jwtUtils.validateJwtToken(accessToken); // access token 형식, 만료시간 등 검사

            // redis에서 access token 비교
            Long userId = getUserIdFromToken(accessToken);
            String accessTokenFromRedis = redisUtil.getByClassType(RedisConst.ACCESS_TOKEN.prefix() + userId, String.class);

            if (accessToken.equals(accessTokenFromRedis)) {
                setUserInSecurityContext(request, accessToken);

            }
            else{
                throw new JwtCustomException(JwtCustomErrorCode.TokenNotMatchWithRedisException);
            }

        }
        catch (ExpiredJwtException e){// accessToken 만료시 refreshToken 검사
            // refresh token이 건재하다면 access token만 재발급하면 된다.
            if(checkRefreshToken(request)){
                throw new JwtCustomException(JwtCustomErrorCode.AccessTokenExpiredException); // AccessToken 만료 에러 보내주기
            }

        }

        return true;
    }

    private boolean checkRefreshToken(HttpServletRequest request){

        try {
            String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER_NAME);

            checkNull(refreshToken);

            jwtUtils.validateJwtToken(refreshToken);

            // redis에서 비교
            Long userId = getUserIdFromToken(refreshToken);
            String refreshTokenFromRedis = redisUtil.getByClassType(RedisConst.REFRESH_TOKEN.prefix() + userId, String.class);

            if(refreshToken.equals(refreshTokenFromRedis)){
                setUserInSecurityContext(request, refreshToken);
            }
            else{
                throw new JwtCustomException(JwtCustomErrorCode.TokenNotMatchWithRedisException);
            }
            // 재발급을 받아야하는데 흠, api + restTemplate 써서 분리해야하나
        }
        catch (ExpiredJwtException ex){ // refreshToken 만료시 error 던지기
            throw new JwtCustomException(JwtCustomErrorCode.RefreshTokenExpiredException);
        }

        return true;
    }

    private void setUserInSecurityContext(HttpServletRequest request, String token) {
        String username = jwtUtils.getSubjectFromToken(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
        null,
                userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private void checkNull(String token){
        if(token == null) throw new JwtCustomException(JwtCustomErrorCode.NotExistJwtTokenException);
    }

    private Long getUserIdFromToken(String token){
        JwtClaimDto userInfo = jwtUtils.getClaimFromToken(token);

        return userInfo.getUserId();
    }


}
