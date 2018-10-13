package com.goldencis.osa.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goldencis.osa.core.entity.Permission;
import com.goldencis.osa.core.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 访问资源权限表 Mapper 接口
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户和资源类型查询权限资源集合
     * @param user 用户对象
     * @param resourceType 资源类型
     * @return 权限资源集合
     */
    List<Permission> findUserPermissionsByResourceType(@Param(value = "user") User user, @Param(value = "resourceType") Integer resourceType);
}
