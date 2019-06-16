package com.example.jjwttest.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjwttest.content.entity.Role;
import com.example.jjwttest.content.entity.User;
import com.example.jjwttest.content.mapper.RoleMapper;
import com.example.jjwttest.content.mapper.UserMapper;
import com.example.jjwttest.springsecurity.entity.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuekangle
 * @date 2019-05-30 15:14
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据username查询User，并查找该User的角色信息
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
        if(null != user){
            List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            user.setRoleList(roles);
        }
        return new JwtUser(user);
    }
}
