package com.goldencis.osa.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by limingchao on 2018/9/25.
 */
@Api(tags = "页面跳转管理")
@Controller
public class PageController {

    @ApiOperation(value = "登录页面")
    @GetMapping(value = "/login")
    public String toLogin() {
        return "login";
    }

    @ApiOperation(value = "系统首页")
    @GetMapping(value = "/index")
    public String homePage() {
        return "index";
    }

    @ApiOperation(value = "用户列表页面")
    @GetMapping(value = "/user/userList")
    public String userIndex() {
        return "user/userList";
    }

    @ApiOperation(value = "通用导航栏页面")
    @GetMapping(value = "/common/topLeft")
    public String topLeftIndex() {
        return "common/topLeft";
    }

    @ApiOperation(value = "策略页面")
    @GetMapping(value = "/strategy/index")
    public String strategyIndex() {
        return "strategy/index";
    }
}
