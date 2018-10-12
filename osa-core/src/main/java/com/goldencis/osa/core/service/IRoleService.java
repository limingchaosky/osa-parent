package com.goldencis.osa.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.goldencis.osa.core.entity.Role;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据role的guid查找角色
     * @param guid 角色guid
     * @return 角色对象
     */
    Role findRoleByGuid(String guid);

    /**
     * 新增角色接口
     * @param role 角色对象
     */
    void saveRole(Role role);
}
