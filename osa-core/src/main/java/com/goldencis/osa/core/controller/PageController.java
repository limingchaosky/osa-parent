package com.goldencis.osa.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by limingchao on 2018/9/25.
 */
@Controller
public class PageController {

    @RequestMapping(value = "/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/index")
    public String homePage() {
        return "index";
    }
}
