package com.goldencis.osa.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.goldencis.osa.core.entity.Permission;
import com.goldencis.osa.core.entity.Resource;

/**
 * <p>
 * 访问资源权限表 服务类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 根据资源类型和资源id查找对应的资源
     * @param resourceType 资源类型
     * @param resourceId 资源id
     * @return 资源对象
     */
    Resource findResourceByResourceTypeAndId(Integer resourceType, Integer resourceId);

    /**
     * 根据权限查找对应的资源
     * @param permission 权限
     * @return 资源
     */
    Resource findResourceByPermission(Permission permission);

    /**
     * 根据权限id查找权限及对应的资源
     * @param id 权限id
     * @return 权限对象
     */
    Permission findPermissionById(Integer id);
}
