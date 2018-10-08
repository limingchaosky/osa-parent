package com.goldencis.osa.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goldencis.osa.common.entity.ResultMsg;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
            return ResultMsg.build(500, e.getMessage());
        }
    }

    @ApiOperation("获取用户分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "起始条数", dataType = "Integer"),
            @ApiImplicitParam(name = "length", value = "每页条数", dataType = "Integer"),
            @ApiImplicitParam(name = "orderType", value = "排序类型", dataType = "String")
    })
    @GetMapping(value = "/getUsersInPage")
    public ResultMsg getUsersInPage(Map map) {
        try {
            logger.info("execute method getUsersInPage...");
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("guid", "1");
            IPage<User> page = new Page<>();


            userService.page(page, wrapper);
            return ResultMsg.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(500, e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResultMsg save() {
        try {
            List<User> userList = new ArrayList<>();

            User user1 = new User();
            user1.setGuid(UUID.randomUUID().toString());
            user1.setUsername("AAA");
            user1.setName("aaa");
            user1.setPassword("password");
            user1.setPhone("11111111");
            userList.add(user1);

            User user2 = new User();
            user2.setGuid(UUID.randomUUID().toString());
            user2.setUsername("BBB");
            user2.setName("bbb");
            user2.setPassword("password");
            user2.setPhone("22222222");
            userList.add(user2);

            User user3 = new User();
            user3.setGuid(UUID.randomUUID().toString());
            user3.setUsername("CCC");
            user3.setName("ccc");
            user3.setPassword("password");
            user3.setPhone("33333333");
            userList.add(user3);

            userService.batchSave(userList);
            return ResultMsg.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(500, e.getMessage());
        }
    }
}
