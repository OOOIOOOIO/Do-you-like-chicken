package com.sh.chicken.global.config.jwt;

import com.sh.chicken.global.exception.CustomException;
import com.sh.chicken.global.exception.CustomErrorCode;
import com.sh.chicken.global.exception.JwtCustomErrorCode;
import com.sh.chicken.global.exception.JwtCustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

//    @Value("${jwt.secret}")
//    private String secret;
//    @Value("${jwt.expireMin}")
//    private Long expireMin;

    private final JwtInfoProperties jwtInfoProperties;
    private final static String ACCESS_TOKEN = "access_token";
    private final static String REFRESH_TOKEN = "refresh_token";
    private static final String BEARER = "Bearer ";

    /**
     * header에서 access_token 가져오기
     */
    public String getAccessTokenFromHeader(HttpServletRequest request) {

        return request.getHeader("access_token");
    }

    /**
     * header에서 refresh_token 가져오기
     */
    public String getRefreshTokenFromHeader(HttpServletRequest request) {
        return request.getHeader("refresh_token");
    }


    /**
     * subject 정보 가져오기(username)
     * subject
     * - username
     */
    public String getSubjectFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtInfoProperties.getSecret().getBytes()) // signature를 secrete key로 설정했는지, publickey로 설정했는지 확인! 나는 secret key로 설정
                .build()
                .parseClaimsJws(token.substring(BEARER.length()))
                .getBody()
                .getSubject();
    }

    /**
     * claim 정보 가져오기
     *
     * JwtClaimDto
     * - userId
     * - username
     * - type
     */
    public JwtClaimDto getClaimFromToken(String token){
        return (JwtClaimDto) Jwts.parserBuilder()
                .setSigningKey(jwtInfoProperties.getSecret().getBytes())
                .build()
                .parseClaimsJws(token.substring(BEARER.length()))
                .getBody()
                .get("userInfo");

    }


    /**
     * JWT 토큰 생성
     * subject
     * - userName
     *
     * claim
     * - JwtClaimDto
     *  - userId
     *  - username
     *  - tokenType
     * expireMin
     * - 1일
     *
     */
    public String generateAccessToken(Long userId, String username) {
        Key key = Keys.hmacShaKeyFor(jwtInfoProperties.getSecret().getBytes());

        return BEARER + Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(username)
                .claim("userInfo", new JwtClaimDto(userId, username, ACCESS_TOKEN))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtInfoProperties.getAccessTokenExpireMin()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Refresh token 생성
     *
     * expireMin
     * - 7일
     */
    public String generateRefreshToken(Long userId, String username) {
        Key key = Keys.hmacShaKeyFor(jwtInfoProperties.getSecret().getBytes());

        return BEARER + Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(username)
                .claim("tokenInfo", new JwtClaimDto(userId, username, REFRESH_TOKEN))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtInfoProperties.getRefreshTokenExpireMin()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     *  JWT 토큰 검사
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtInfoProperties.getSecret().getBytes()) // signature를 secrete key로 설정했는지, publickey로 설정했는지 확인! 나는 secret key로 설정
                    .build()
                    .parseClaimsJws(token.substring(BEARER.length()));  // 여기서 Runtime Exception이 던져진다.

            return true;
        } catch (SignatureException e) {
            throw new JwtCustomException(JwtCustomErrorCode.SignatureException);
        } catch (MalformedJwtException e) {
            throw new JwtCustomException(JwtCustomErrorCode.MalformedJwtException);
        } catch (ExpiredJwtException e) {
            throw new JwtCustomException(JwtCustomErrorCode.AccessTokenExpiredException);
        } catch (UnsupportedJwtException e) {
            throw new JwtCustomException(JwtCustomErrorCode.UnsupportedJwtException);
        } catch (IllegalArgumentException e) {
            throw new CustomException(CustomErrorCode.IllegalArgumentException);
        }

    }




}