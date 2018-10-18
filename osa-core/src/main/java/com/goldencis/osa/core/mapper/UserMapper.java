package com.goldencis.osa.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goldencis.osa.core.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表-定义用户基本信息 Mapper 接口
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据guid查询用户
     * @param guid 用户guid
     * @return 用户对象
     */
    User findUserByGuid(String guid);

    /**
     * 根据参数统计用户数量
     * @param params 参数map
     * @return 总数
     */
    int countUsersInPage(Map<String, Object> params);

    /**
     * 根据参数查询用户集合
     * @param params 参数map
     * @return 用户集合
     */
    List<User> getUsersInPage(Map<String, Object> params);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户对象
     */
    User findUserByUsername(String username);
}
