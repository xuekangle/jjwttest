package com.example.jjwttest.springsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjwttest.content.entity.User;
import com.example.jjwttest.content.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by echisan on 2018/6/23
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String,String> registerUser){
        User user = new User();
        String username = registerUser.get("username");
        user.setUsername(username);
        user.setPassword(DigestUtils.md5DigestAsHex(registerUser.get("password").getBytes()));
        //user.getAuthorities().add(new Role("ROLE_USER"));
        User one = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if(null != one){
            log.warn("the user about username:" + username + " is exits.");
            return "the user about username:" + username + " is exits.";
        }
        boolean save = userService.save(user);
        return Boolean.toString(save);
    }
}
