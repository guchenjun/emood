package com.hznu.emood.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hznu.emood.annotation.PassToken;
import com.hznu.emood.annotation.UserLoginToken;
import com.hznu.emood.model.User;
import com.hznu.emood.service.UserService;
import com.hznu.emood.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Reference
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        // 如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 检查是否有passtoekn注解，有则跳过
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    throw new RuntimeException("401");
                }
                User user = userService.getUserById(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }

                // 验证token
                try {
                    JwtUtil.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("500");
                }
                return true;
            }
        }
        return true;
    }
}
