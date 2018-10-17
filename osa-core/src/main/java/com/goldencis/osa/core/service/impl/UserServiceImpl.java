package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.constants.ConstantsDto;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.mapper.UserMapper;
import com.goldencis.osa.core.service.IUserService;
import com.goldencis.osa.core.utils.QueryUtils;
import com.goldencis.osa.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Override
    @Transactional(readOnly = true)
    public User findUserByGuid(String guid) {
        return userMapper.findUserByGuid(guid);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUserName(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        return user;
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

        //增加排序条件，默认按创建时间的倒序排列
        QueryUtils.setQeryOrderByParamsMap(wrapper, params, ConstantsDto.ORDER_TYPE_DESC, "create_time");
        return wrapper;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkUserNameDuplicate(User user) {

        User preUser = this.findUserByUserName(user.getUsername());

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void saveUser(User user) {
        user.setGuid(UUID.randomUUID().toString());
        user.setVisible(ConstantsDto.CONST_TRUE);
        user.setCreateTime(LocalDateTime.now());

        this.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void updateUserByGuid(User user) {

        this.updateById(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteUserByGuid(String guid) {
        this.removeById(guid);
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        String username = SecurityUtils.getCurrentUser();
        if (!StringUtils.isEmpty(username)) {
            User user = this.findUserByUserName(username);
            return user;
        }
        return null;
    }

    @Override
    public IPage<User> getUsersInPage(IPage<User> page, Map<String, String> params) {
        //为模糊查询的添加增加%%符号
        QueryUtils.addFuzzyQuerySymbols(params);
        Map<String, Object> paramMap = QueryUtils.formatPageParams(params);

        //统计用户总数量
        int total = userMapper.countUsersInPage(paramMap);
        //带参数的分页查询
        List<User> userList = userMapper.getUsersInPage(paramMap);

        page.setTotal(total);
        page.setRecords(userList);

        return page;
    }
}