package com.goldencis.osa.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goldencis.osa.core.entity.Usergroup;

import java.util.Map;

/**
 * <p>
 * 用户组表 服务类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface IUsergroupService extends IService<Usergroup> {

    /**
     * 将请求中的查询参数转化为用户组包装类
     * @param params 请求中的参数Map
     * @return 查询的包装类
     */
    QueryWrapper<Usergroup> parseParams2QueryWapper(Map<String, String> params);
}
