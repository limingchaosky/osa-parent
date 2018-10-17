package com.goldencis.osa.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goldencis.osa.core.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户guid查询用户对应的角色集合
     * @param guid 用户guid
     * @return 角色集合
     */
    List<Role> getRoleListByUserguid(@Param(value = "guid") String guid);
}
