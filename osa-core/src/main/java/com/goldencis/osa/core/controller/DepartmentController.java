package com.goldencis.osa.core.controller;


import com.goldencis.osa.core.service.IDepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门信息表 前端控制器
 * </p>
 *
 * @author limingchao
 * @since 2018-10-17
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;


}
