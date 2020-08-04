package com.dxp.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页
 *
 * @author dxp
 * 2020/7/26 2:11 下午
 */
@Controller
public class MainController {

    @GetMapping("/loginSuc")
    @ResponseBody
    private String index() {
        return "login suc";
    }

    @GetMapping("/login_page")
    private String login_page() {
        return "login_page";
    }

}
