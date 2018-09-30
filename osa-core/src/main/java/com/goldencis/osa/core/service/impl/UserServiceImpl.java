package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.mapper.UserMapper;
import com.goldencis.osa.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户信息表-定义用户基本信息 服务实现类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public User findUserByGuid(String guid) {
        return userMapper.findUserByGuid(guid);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void batchSave(List<User> userList) throws Exception {
        for (User user : userList) {
            if ("CCC".equals(user.getUsername())) {
                throw new RuntimeException();
            }
            userMapper.insert(user);
        }
    }
}