package com.example.jjwttest.content.service.impl;

import com.example.jjwttest.content.entity.UserRole;
import com.example.jjwttest.content.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jjwttest.content.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuekangle
 * @since 2019-05-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
