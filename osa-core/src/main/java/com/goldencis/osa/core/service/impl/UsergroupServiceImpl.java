package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.constants.ConstantsDto;
import com.goldencis.osa.core.entity.Usergroup;
import com.goldencis.osa.core.mapper.UsergroupMapper;
import com.goldencis.osa.core.service.IUsergroupService;
import com.goldencis.osa.core.utils.QueryUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Service
public class UsergroupServiceImpl extends ServiceImpl<UsergroupMapper, Usergroup> implements IUsergroupService {

    @Override
    public QueryWrapper<Usergroup> parseParams2QueryWapper(Map<String, String> params) {
        QueryWrapper<Usergroup> wrapper = new QueryWrapper<>();
        //添加模糊查询条件
        QueryUtils.setQeryConditionByParamsMap(wrapper, params, "name");

        //增加排序条件，默认按创建时间的倒序排列
        QueryUtils.setQeryOrderByParamsMap(wrapper, params, ConstantsDto.ORDER_TYPE_DESC, "create_time");
        return wrapper;
    }
}
