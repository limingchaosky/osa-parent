package com.goldencis.osa.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户信息表-定义用户基本信息
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.唯一标示
     */
    @TableId(value = "guid", type = IdType.UUID)
    private String guid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户权限集合
     */
    @TableField(exist = false)
    private Set<GrantedAuthority> authorities;

    /**
     * 所属部门
     */
//    private Integer department;

    /**
     * 所属部门
     */
    @TableField(exist = false)
    private Department department;

    /**
     * 姓名
     */
    private String name;

    /**
     * 角色集合
     */
    @TableField(exist = false)
    private List<Role> roles;

    /**
     * 认证方式：1为密码，2为密码+APP口令，3为密码+动态令牌，4为密码+短信平台，5为密码+第三方USBKEY，6为密码+OTP自写证书认证
     */
    private Integer authenticationMethod;

    /**
     * 是否可见
     */
    private Integer visible;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户状态可用：11为可用，10为不可用(锁定)
     */
    private Integer status;

    /**
     * 外观样式
     */
    private String skin;

    /**
     * 最后一次正常登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 错误登录次数
     */
    private Integer errorLoginCount;

    /**
     * 最近错误登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime errorLoginLastTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Override
    protected Serializable pkVal() {
        return this.guid;
    }

}
