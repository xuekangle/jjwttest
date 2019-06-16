package com.example.jjwttest.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

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
public class Role extends Model<Role> implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    public Role(){}

    public Role(String name){
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
