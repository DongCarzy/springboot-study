package com.dxp.security.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 *
 * @author dxp
 * 2020/7/26 2:11 下午
 */
@Controller
public class MainController {

    @GetMapping("/index")
    private String index(){
        return "index";
    }

    @GetMapping("/login_page")
    private String login_page(){
        return "login_page";
    }

//    @GetMapping("/login")
//    private String login(String username, String password){
//        return "index";
//    }
}
