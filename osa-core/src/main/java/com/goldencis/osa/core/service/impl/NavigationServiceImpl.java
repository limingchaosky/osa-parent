package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.entity.Navigation;
import com.goldencis.osa.core.mapper.NavigationMapper;
import com.goldencis.osa.core.service.INavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 页签-导航信息表 服务实现类
 * </p>
 *
 * @author limingchao
 * @since 2018-09-27
 */
@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements INavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    @Override
    public List<Navigation> getAllNavigations() {
        QueryWrapper<Navigation> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("level", "compositor");
        List<Navigation> navigationList = navigationMapper.selectList(wrapper);
        return navigationList;
    }

    @Override
    public List<Navigation> formatNavigationTree(List<Navigation> navigationList) {
        List<Navigation> tree = new ArrayList<>();
        Map<Integer, Navigation> navigationMap = navigationList.stream().collect(Collectors.toMap(Navigation::getId, navigation -> navigation));
        navigationList.forEach(navigation -> {
            if (StringUtils.isEmpty(navigation.getParentId())) {
                tree.add(navigation);
            } else {
                this.setChildNavigation(navigationMap.get(navigation.getParentId()), navigation);
            }
        });
        return tree;
    }

    protected void setChildNavigation(Navigation parentNavigation, Navigation childNavigation) {
        if (parentNavigation.getSub() == null) {
            parentNavigation.setSub(new ArrayList<>());
        }
        parentNavigation.getSub().add(childNavigation);
    }
}
