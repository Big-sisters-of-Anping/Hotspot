package com.example.demo.controller.interceptor;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类名称: TokenInterceptor
 * 类描述: 根据Token拦截请求
 *
 * @author: wqy
 * 创建时间: 2020/6/16 5:14 下午
 * Version 1.0
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    public static final String LOGIN_IDENTITY_KEY = "HOTSPOT_LOGIN_IDENTITY";

    public static boolean checkToken(String token){
        if (TokenUtil.FindToken(token)){
            return true;
        }else
            return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) return super.preHandle(request, response, handler);
        HandlerMethod method = (HandlerMethod)handler;
        TokenLimit permission = method.getMethodAnnotation(TokenLimit.class);
        // 请求 没有TokenLimit注解 或 TokenLimit注解为true(default) 的接口 都被拦截
        if (permission == null || permission.CheckToken()) {
            String token = CookieUtil.getValue(request, LOGIN_IDENTITY_KEY);
            if (token == null || !checkToken(token)) {  // token不通过
//                response.sendRedirect(request.getContextPath() + "/toLogin");
//                request.getRequestDispatcher("/toLogin").forward(request, response);
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}