package com.example.jjwttest.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuekangle
 * @since 2019-05-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Model<User>  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    @TableField(exist = false)
    private List<Role> roleList;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
