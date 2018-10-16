package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.entity.Permission;
import com.goldencis.osa.core.entity.Resource;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.mapper.PermissionMapper;
import com.goldencis.osa.core.service.IPermissionService;
import com.goldencis.osa.core.utils.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 访问资源权限表 服务实现类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private Map<Integer, BaseMapper<? extends Resource>> resourceMapperMap;

    @Override
    public Resource findResourceByResourceTypeAndId(Integer resourceType, Integer resourceId) {
        Resource resource = this.getResourceMapper(resourceType).selectById(resourceId);
        return resource;
    }

    @Override
    public List<? extends Resource> findResourceListByResourceType(Integer resourceType) {
        List<? extends Resource> resourceList = this.getResourceMapper(resourceType).selectList(new QueryWrapper<>());
        return resourceList;
    }

    @Override
    public Resource findResourceByPermission(Permission permission) {
        return this.findResourceByResourceTypeAndId(permission.getResourceType(), permission.getResourceId());
    }

    @Override
    public Permission findPermissionById(Integer id) {
        Permission permission = permissionMapper.selectById(id);
        Resource resource = this.findResourceByPermission(permission);
        permission.setResource(resource);
        return permission;
    }

    @Override
    public List<Resource> findUserPermissionsByResourceType(User user, Integer resourceType) {
        //根据用户和资源类型查询权限集合
        List<Permission> permissionList = permissionMapper.findUserPermissionsByResourceType(user, resourceType);
        List<Integer> resourceIdList = permissionList.stream().map(Permission::getResourceId).collect(Collectors.toList());

        //根据资源的类型获取对应Mapper
        BaseMapper resourceMapper = this.getResourceMapper(resourceType);
        //查询对应资源类型里，对应资源id集合的资源
        List<Resource> resourceList = resourceMapper.selectList(new QueryWrapper<Resource>().in("id", resourceIdList));
        return resourceList;
    }

    @Override
    public Map<String, List<Resource>> findUserPermissions(User user) {
        Map<String, List<Resource>> resourceMap = new HashMap<>();
        List<ResourceType> resourceTypeList = ResourceType.getResourceTypeList();
        for (ResourceType resourceType : resourceTypeList) {
            resourceMap.put(resourceType.getName(), this.findUserPermissionsByResourceType(user, resourceType.getValue()));
        }
        return resourceMap;
    }

    /**
     * 根据资源类型，获取对应的mapper
     * @param resourceType 资源类型
     * @return 对应的mapper
     */
    protected BaseMapper<? extends Resource> getResourceMapper(Integer resourceType) {
        return resourceMapperMap.get(resourceType);
    }
}
