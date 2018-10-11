package com.goldencis.osa.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goldencis.osa.common.entity.ResultMsg;
import com.goldencis.osa.core.constants.ConstantsDto;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.service.IUserService;
import com.goldencis.osa.core.utils.QueryUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户信息表-定义用户基本信息 前端控制器
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation("获取用户分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "起始条数", dataType = "Integer"),
            @ApiImplicitParam(name = "length", value = "每页条数", dataType = "Integer"),
            @ApiImplicitParam(name = "searchStr", value = "每页条数", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "orderColumn", value = "排序字段", dataType = "String"),
            @ApiImplicitParam(name = "orderType", value = "排序类型", dataType = "String")
    })
    @GetMapping(value = "/getUsersInPage")
    public ResultMsg getUsersInPage(@RequestParam Map<String, String> params) {
        try {
            //获取分页参数
            IPage<User> page = QueryUtils.paresParams2Page(params);

            //解析参数，封装查询包装类
            QueryWrapper<User> wrapper = userService.parseParams2QueryWapper(params);

            //分页查询
            userService.page(page, wrapper);
            return ResultMsg.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(ConstantsDto.RESPONSE_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "通过guid获取用户信息")
    @ApiImplicitParam(name = "guid", value = "用户guid", required = true, paramType = "path", dataType = "String")
    @Cacheable(value = "users", key = "#guid")
    @GetMapping(value = "/user/{guid}")
    public ResultMsg findUserByGuid(@PathVariable("guid") String guid) {

        try {
            User user = userService.findUserByGuid(guid);
            return ResultMsg.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(ConstantsDto.RESPONSE_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "真实姓名", dataType = "String"),
            @ApiImplicitParam(name = "email", value = "email", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "电话号码", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "账号状态", dataType = "String"),
    })
    @PostMapping(value = "/user")
    public ResultMsg save(User user) {
        try {
            //检查用户名是否重复
            boolean flag = userService.checkUserNameDuplicate(user);

            if (!flag) {
                return ResultMsg.build(ConstantsDto.CONST_FALSE, "用户名重复！");
            }

            userService.save(user);

            return ResultMsg.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(ConstantsDto.RESPONSE_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "编辑用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "真实姓名", dataType = "String"),
            @ApiImplicitParam(name = "email", value = "email", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "电话号码", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "账号状态", dataType = "String"),
    })
    @PutMapping(value = "/user")
    public ResultMsg edit(User user) {
        try {
            //检查用户名是否重复
            boolean flag = userService.checkUserNameDuplicate(user);

            if (!flag) {
                return ResultMsg.build(ConstantsDto.CONST_FALSE, "用户名重复！");
            }

            userService.updateById(user);
            return ResultMsg.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(ConstantsDto.RESPONSE_ERROR, e.getMessage());
        }
    }

    public ResultMsg delete() {
        try {
            return ResultMsg.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(ConstantsDto.RESPONSE_ERROR, e.getMessage());
        }
    }
}
