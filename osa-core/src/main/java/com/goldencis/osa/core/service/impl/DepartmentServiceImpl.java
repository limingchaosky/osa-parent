package com.goldencis.osa.core.service.impl;

import com.goldencis.osa.core.entity.Department;
import com.goldencis.osa.core.mapper.DepartmentMapper;
import com.goldencis.osa.core.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author limingchao
 * @since 2018-10-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
