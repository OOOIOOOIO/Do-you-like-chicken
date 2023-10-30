package com.sh.chicken.global.session.interceptor.sessionCheckInterceptor;

import com.sh.chicken.global.common.SessionConst;
import com.sh.chicken.global.session.resolver.usersession.UserInfoFromSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class UserSessionCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            HttpSession session = request.getSession(false);
            UserInfoFromSessionDto userInfo = (UserInfoFromSessionDto)session.getAttribute(SessionConst.COMMON_USER.getRule());

        } catch (Exception e) {
            throw new RuntimeException("interceptor : 유저 없음");
        }

        return true;

    }

}
