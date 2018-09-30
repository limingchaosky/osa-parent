package com.goldencis.osa.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goldencis.osa.core.entity.Navigation;
import com.goldencis.osa.core.mapper.NavigationMapper;
import com.goldencis.osa.core.service.INavigationService;
import org.springframework.stereotype.Service;

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

}
