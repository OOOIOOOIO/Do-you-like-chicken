package com.sh.chicken.global.config.jwt;

import com.sh.chicken.global.exception.CustomException;
import com.sh.chicken.global.exception.CustomErrorCode;
import com.sh.chicken.global.exception.JwtCustomErrorCode;
import com.sh.chicken.global.exception.JwtCustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
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
        } catch (UsernameNotFoundException e) {
            throw new CustomException(CustomErrorCode.UsernameNotFoundException);
        }
    }



}
