package com.goldencis.osa.core.controller;


import com.goldencis.osa.common.entity.ResultMsg;
import com.goldencis.osa.common.utils.ListUtils;
import com.goldencis.osa.core.entity.Navigation;
import com.goldencis.osa.core.service.INavigationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 页签-导航信息表 前端控制器
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    private INavigationService navigationService;

    @ApiOperation(value = "获取用户权限的全部菜单")
    @GetMapping(value = "/getNavigationTreeByUser")
    public ResultMsg getNavigationTreeByUser() {

        try {
            //获取全部菜单集合
            List<Navigation> navigationList = navigationService.getAllNavigations();

            //将菜单集合转化为菜单树
            if (!ListUtils.isEmpty(navigationList)) {
                navigationList = navigationService.formatNavigationTree(navigationList);
            }

            return ResultMsg.ok(navigationList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(500, e.getMessage());
        }
    }

}
