package com.example.jjwttest.springsecurity.entity;

import com.example.jjwttest.content.entity.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author xuekangle
 * @date 2019-05-30 15:29
 */
@Data
@Slf4j
public class JwtUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private List<? extends GrantedAuthority> authorities;

    public JwtUser(){}

    public JwtUser(User user){
       id = user.getId();
       username = user.getUsername();
       password = user.getPassword();
       authorities = user.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 获取当前登陆用户对应的对象。
     * @return
     */
    public static JwtUser getCurUser(){
        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        log.info("当前用户:" + userDetails);
        return userDetails;
    }
}
