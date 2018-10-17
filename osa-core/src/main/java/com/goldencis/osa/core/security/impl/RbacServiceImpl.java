package com.goldencis.osa.core.security.impl;

import com.goldencis.osa.core.constants.ConstantsDto;
import com.goldencis.osa.core.entity.Navigation;
import com.goldencis.osa.core.entity.Operation;
import com.goldencis.osa.core.entity.Resource;
import com.goldencis.osa.core.entity.User;
import com.goldencis.osa.core.listener.ConfigListener;
import com.goldencis.osa.core.security.IRbacService;
import com.goldencis.osa.core.service.IPermissionService;
import com.goldencis.osa.core.service.IUserService;
import com.goldencis.osa.core.utils.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by limingchao on 2018/10/16.
 */
@Component("rbacService")
public class RbacServiceImpl implements IRbacService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //获取用户的认证信息
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
            //获取用户对象
            String userName = ((UserDetails) principal).getUsername();
            User user = userService.findUserByUserName(userName);

            //权限的URL集合，用于存放用户所拥有权限的所有URL
            Set<String> urls = new HashSet<>(); // 数据库读取

            //增加默认的首页index权限
            urls.add("/");

            //获取页面权限
            List<Resource> navigationList = permissionService.findUserPermissionsByResourceType(user, ResourceType.NAVIGATION.getValue());
            navigationList.stream().forEach(resource -> urls.add(((Navigation) resource).getUrl()));

            //获取功能权限
            List<Resource> operationList = permissionService.findUserPermissionsByResourceType(user, ResourceType.OPERATION.getValue());
            operationList.stream().forEach(resource -> urls.add(((Operation) resource).getUrlPartten()));


            String contextPath = ConfigListener.getConf().get(ConstantsDto.CONTEXT_PATH);
            String requestURI = request.getRequestURI().substring(contextPath.length());
            System.out.println(requestURI);
            // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
            for (String url : urls) {
                if (antPathMatcher.match(url, requestURI)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
