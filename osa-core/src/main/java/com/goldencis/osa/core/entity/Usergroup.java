package com.goldencis.osa.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_usergroup")
public class Usergroup extends Model<Usergroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.唯一标示
     */
    @TableId(value = "guid", type = IdType.AUTO)
    private String guid;

    /**
     * 用户组名称
     */
    private String name;

    /**
     * 用户组备注
     */
    private String comment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    private String createBy;


    @Override
    protected Serializable pkVal() {
        return this.guid;
    }

}
