package com.goldencis.osa.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门信息表
 * </p>
 *
 * @author limingchao
 * @since 2018-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级部门Id
     */
    private Integer parentId;

    /**
     * 备注信息
     */
    private String departmentRemark;

    /**
     * 部门负责人
     */
    private String owner;

    /**
     * 部门电话
     */
    private String departmentTel;

    /**
     * 路径
     */
    private String treePath;

    /**
     * 状态
     */
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
