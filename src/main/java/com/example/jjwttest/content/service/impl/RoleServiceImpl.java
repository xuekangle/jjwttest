package com.example.jjwttest.content.service.impl;

import com.example.jjwttest.content.entity.Role;
import com.example.jjwttest.content.mapper.RoleMapper;
import com.example.jjwttest.content.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
