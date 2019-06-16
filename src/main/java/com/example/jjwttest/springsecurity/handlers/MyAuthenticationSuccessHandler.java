package com.example.jjwttest.springsecurity.handlers;

import com.example.jjwttest.springsecurity.entity.JwtUser;
import com.example.jjwttest.springsecurity.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理类,返回jwt
 * @author xuekangle
 * @date 2019-05-20 13:11
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        //从authentication中获取用户信息
        final JwtUser jwtUser = (JwtUser)authentication.getPrincipal();
        //生成jwt
        String token = JwtTokenUtil.createToken(jwtUser);
        response.setHeader("token","Bearer " + token);
        response.getWriter().write(token);
        response.getWriter().flush();



    }
}
