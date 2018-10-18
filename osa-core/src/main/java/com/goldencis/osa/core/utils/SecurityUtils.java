package com.goldencis.osa.core.utils;

import com.goldencis.osa.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by limingchao on 2018/10/13.
 */
@Component
public class SecurityUtils {

    private static SecurityUtils securityUtils;

    @Autowired
    private IUserService userService;

    @PostConstruct
    public void init() {
        securityUtils = this;
        securityUtils.userService = this.userService;
    }

    public static com.goldencis.osa.core.entity.User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String username = ((User) authentication.getPrincipal()).getUsername();
            return securityUtils.userService.findUserByUserName(username);
        } else {
            return null;
        }
    }

}
