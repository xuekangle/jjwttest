package com.example.jjwttest.content.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjwttest.content.entity.User;
import com.example.jjwttest.content.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuekangle
 * @since 2019-05-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/list")
    public Object getUserList(){

        List<User> list = iUserService.list(new QueryWrapper<>());
        return list;
    }
}

