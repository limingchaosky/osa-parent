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
 * 功能操作表
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_operation")
public class Operation extends Model<Operation> {

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
     * 功能操作编码
     */
    private String code;

    /**
     * 功能操作url样式
     */
    private String urlPartten;

    /**
     * 功能描述
     */
    private String description;

    /**
     * 父级功能Id
     */
    private Integer parentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
