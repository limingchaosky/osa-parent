package com.goldencis.osa.core.controller;


import com.goldencis.osa.common.entity.ResultMsg;
import com.goldencis.osa.core.entity.Permission;
import com.goldencis.osa.core.entity.Resource;
import com.goldencis.osa.core.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    @ApiOperation(value = "根据资源类型和资源id查找对应的资源")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "resourceType", value = "资源类型", required = true, paramType = "path", dataType = "Integer"),
        @ApiImplicitParam(name = "resourceId", value = "资源id", paramType = "path", dataType = "Integer")
    })
    @GetMapping(value = "/resource/{resourceType}/{resourceId}")
    public ResultMsg findResourceByResourceTypeAndId(@PathVariable(value = "resourceType") Integer resourceType, @PathVariable(value = "resourceId", required = false) Integer resourceId) {
        try {
            Object result;
            if (StringUtils.isEmpty(resourceId)) {
                //根据资源类型查找对应的全部资源集合
                result = permissionService.findResourceListByResourceType(resourceType);
            } else {
                //根据资源类型和资源id查找对应的资源
                result = permissionService.findResourceByResourceTypeAndId(resourceType, resourceId);
            }

            return ResultMsg.ok(result);
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
