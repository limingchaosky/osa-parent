package com.goldencis.osa.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goldencis.osa.core.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表-定义用户基本信息 服务类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface IUserService extends IService<User> {

    /**
     * 通过guid查询用户信息
     * @param guid 用户guid
     * @return 用户对象
     */
    User findUserByGuid(String guid);

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUserName(String username);

    void batchSave(List<User> userList) throws Exception;

    /**
     * 将请求中的查询参数转化为包装类
     * @param params 请求中的参数Map
     * @return 查询的包装类
     */
    QueryWrapper<User> parseParams2QueryWapper(Map<String, String> params);

    /**
     * 检查用户名是否重复
     * @param user 请求中提交的用户对象
     * @return 是否可用，true为该账户名可用，false为该账户名不可用。
     */
    boolean checkUserNameDuplicate(User user);
}
