package com.example.jjwttest.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jjwttest.content.entity.Role;
import org.apache.ibatis.annotations.Param;
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
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select A.id,A.name from role A left join user_role B on A.id = B.role_id where B.user_id = #{userId}")
    List<Role> getRolesByUserId(@Param("userId") Long userId);
}
