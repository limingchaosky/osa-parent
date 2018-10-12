package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.entity.Role;
import com.goldencis.osa.core.mapper.RoleMapper;
import com.goldencis.osa.core.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public Role findRoleByGuid(String guid) {
        Role role = roleMapper.selectById(guid);
        return role;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void saveRole(Role role) {
        role.setGuid(UUID.randomUUID().toString());
        role.setCreateTime(LocalDateTime.now());
        roleMapper.insert(role);
    }



}
