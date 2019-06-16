package com.example.jjwttest.springsecurity.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 其他异常处理类，如请求路径不存在等
 * 如果不配置此类，则SpringSecurity默认会跳转到登陆页面
 * @author xuekangle
 * @date 2019-05-20 13:10
 */
@Component
@Slf4j
public class MyAuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.error("AuthenticationEntryPoint检测到异常：" + authException);
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("AuthenticationEntryPoint检测到异常：" + authException);
        response.getWriter().flush();
    }
}
