package com.goldencis.osa.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 页签-导航信息表
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_navigation")
public class Navigation extends Model<Navigation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键（自增长）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 排序
     */
    private Integer compositor;

    /**
     * 页签显示名称
     */
    private String name;

    /**
     * 页签跳转链接
     */
    private String url;

    /**
     * 父级页签Id
     */
    private Integer parentId;

    /**
     * 页签图标
     */
    private String icon;

    /**
     * 页签级别
     */
    private Integer level;

    @TableField(exist = false)
    private List<Navigation> sub;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
