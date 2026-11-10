package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.example.expert.domain.user.enums.UserRole.ADMIN;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        if (request.getAttribute("userRole") != ADMIN) {
            return false;
        }
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;   // true면 컨트롤러로 진행, false면 여기서 중단
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
        long start = (long) request.getAttribute("startTime");
        System.out.println("소요 시간: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("요청 경로: " + request.getRequestURL());
    }


}
