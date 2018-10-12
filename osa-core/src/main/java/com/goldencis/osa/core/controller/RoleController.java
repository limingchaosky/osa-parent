package com.goldencis.osa.core.controller;


import com.goldencis.osa.common.entity.ResultMsg;
import com.goldencis.osa.core.entity.Role;
import com.goldencis.osa.core.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Api(tags = "角色信息管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "根据角色guid获取用户信息")
    @ApiImplicitParam(name = "guid", value = "角色guid", required = true, type = "path", dataType = "String")
    @GetMapping(value = "/role/{guid}")
    public ResultMsg findRoleByGuid(@PathVariable(value = "guid") String guid) {
        try {

            Role role = roleService.findRoleByGuid(guid);

            return ResultMsg.ok(role);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(500, e.getMessage());
        }
    }

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/role")
    public ResultMsg save(Role role) {
        try {
            roleService.saveRole(role);

            return ResultMsg.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(500, e.getMessage());
        }
    }

}
