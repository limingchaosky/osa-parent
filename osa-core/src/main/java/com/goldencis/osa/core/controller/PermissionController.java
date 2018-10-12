package com.goldencis.osa.core.controller;


import com.goldencis.osa.common.entity.ResultMsg;
import com.goldencis.osa.core.entity.Permission;
import com.goldencis.osa.core.entity.Resource;
import com.goldencis.osa.core.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 访问资源权限表 前端控制器
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Api(tags = "用户权限管理")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping(value = "/resource/{resourceType}/{resourceId}")
    public ResultMsg findResourceByResourceTypeAndId(@PathVariable(value = "resourceType") Integer resourceType, @PathVariable(value = "resourceId") Integer resourceId) {
        try {
//            Permission permission = permissionService.findPermissionByResourceTypeAndId(resourceType, resourceId);
            //根据资源类型和资源id查找对应的资源
            Resource resource = permissionService.findResourceByResourceTypeAndId(resourceType, resourceId);

            return ResultMsg.ok(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(500, e.getMessage());
        }
    }

    @ApiOperation(value = "根据权限id查找权限及对应的资源")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, paramType = "path", dataType = "Integer")
    @GetMapping(value = "/permission/{id}")
    public ResultMsg findPermissionById(@PathVariable(value = "id") Integer id) {
        try {
            //根据权限id查找权限及对应的资源
            Permission permission = permissionService.findPermissionById(id);

            return ResultMsg.ok(permission);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(500, e.getMessage());
        }
    }

}
