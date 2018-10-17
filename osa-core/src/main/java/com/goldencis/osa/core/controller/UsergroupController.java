package com.goldencis.osa.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goldencis.osa.common.entity.ResultMsg;
import com.goldencis.osa.core.constants.ConstantsDto;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.entity.Usergroup;
import com.goldencis.osa.core.service.IUsergroupService;
import com.goldencis.osa.core.utils.QueryUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 用户组表 前端控制器
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Api(tags = "用户组管理")
@RestController
@RequestMapping("/usergroup")
public class UsergroupController {

    @Autowired
    private IUsergroupService usergroupService;

    @ApiOperation(value = "查询用户组列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "起始条数", dataType = "Integer"),
            @ApiImplicitParam(name = "length", value = "每页条数", dataType = "Integer"),
            @ApiImplicitParam(name = "searchStr", value = "每页条数", dataType = "String"),
            @ApiImplicitParam(name = "orderColumn", value = "排序字段", dataType = "String"),
            @ApiImplicitParam(name = "orderType", value = "排序类型", dataType = "String")
    })
    @GetMapping(value = "/getUserGroupsInPage")
    public ResultMsg getUserGroupsInPage(@RequestParam Map<String, String> params) {

        try {
            //获取分页参数
            IPage<Usergroup> page = QueryUtils.paresParams2Page(params);

            //解析参数，封装查询包装类
            QueryWrapper<Usergroup> wrapper = usergroupService.parseParams2QueryWapper(params);

            //分页查询
            usergroupService.page(page, wrapper);
            return ResultMsg.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.build(ConstantsDto.RESPONSE_ERROR, e.getMessage());
        }
    }

}
