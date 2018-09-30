package com.goldencis.osa.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goldencis.osa.core.entity.User;

/**
 * <p>
 * 用户信息表-定义用户基本信息 Mapper 接口
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
public interface UserMapper extends BaseMapper<User> {

    User findUserByGuid(String guid);
}
