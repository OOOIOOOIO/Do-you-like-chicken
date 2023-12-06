package com.sh.chicken.global.resolver;

import com.sh.chicken.global.config.jwt.JwtClaimDto;
import com.sh.chicken.global.config.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserInfoFromHeaderArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtils jwtUtils;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.getParameterType().equals(UserInfoFromHeaderDto.class);
    }

    /**
     * 헤더에서 jwt accessToken 추출 -> email 추출
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = jwtUtils.getAccessTokenFromHeader((HttpServletRequest) webRequest);

        JwtClaimDto userInfo = jwtUtils.getClaimFromToken(accessToken);


        log.info("========== UserInfoFromHeaderArgumentResolver ==========");
        log.info("========== Get username : " + userInfo.getUsername() + " ==========");

        return new UserInfoFromHeaderDto(userInfo.getUserId(), userInfo.getUsername());
    }

}
