package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.mapper.UserMapper;
import com.goldencis.osa.core.service.IUserService;
import com.goldencis.osa.core.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

    @Override
    public User getUserByUserName(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().ge("username", username));
        return user;
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

    @Override
    public QueryWrapper<User> parseParams2QueryWapper(Map<String, String> params) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //增加查询条件
        String searchStr = params.get("searchStr");
        if (!StringUtils.isEmpty(searchStr)) {
            wrapper.nested(w -> w.like("name", searchStr).or().like("guid", searchStr));
        }

        //增加时间条件
        QueryUtils.setQeryTimeByParamsMap(wrapper, params, "create_time");



        QueryUtils.setQeryOrderByParamsMap(wrapper, params);
        return wrapper;
    }

    @Override
    public boolean checkUserNameDuplicate(User user) {

        User preUser = this.getUserByUserName(user.getUsername());

        //判断数据库是否有该记录，不存在即可用，返回true，如果有继续判断
        if (preUser != null) {
            //比较两个对象的id，若一致，是同一个对象没有改变名称的情况，返回可用true。
            if (preUser.getGuid().equals(user.getGuid())) {
                return true;
            }
            //若果不同，说明为两个用户，名称重复，不可用，返回false
            return false;
        }
        return true;
    }
}