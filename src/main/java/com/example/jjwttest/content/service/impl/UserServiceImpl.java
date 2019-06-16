package com.example.jjwttest.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jjwttest.content.entity.User;
import com.example.jjwttest.content.mapper.UserMapper;
import com.example.jjwttest.content.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuekangle
 * @since 2019-05-23
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
