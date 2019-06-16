package com.example.jjwttest.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jjwttest.content.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuekangle
 * @since 2019-05-23
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT A.NAME AS roleName,C.url FROM role AS A LEFT JOIN role_permission B ON A.id=B.role_id LEFT JOIN permission AS C ON B.permission_id=C.id")
    List<Permission> getPermissions();
}
