package com.example.jjwttest.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jjwttest.content.entity.Permission;
import com.example.jjwttest.content.mapper.PermissionMapper;
import com.example.jjwttest.content.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
