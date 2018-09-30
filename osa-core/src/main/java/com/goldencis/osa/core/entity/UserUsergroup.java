package com.goldencis.osa.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户和用户组关联表
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_usergroup")
public class UserUsergroup extends Model<UserUsergroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户guid
     */
    @TableId(value = "user_guid", type = IdType.AUTO)
    private String userGuid;

    /**
     * 用户组guid
     */
    private String usergroupGuid;


    @Override
    protected Serializable pkVal() {
        return this.userGuid;
    }

}
