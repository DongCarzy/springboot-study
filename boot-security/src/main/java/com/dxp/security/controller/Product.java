package com.dxp.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 产品页
 *
 * @author dxp
 * 2020/7/26 2:18 下午
 */
@Controller
public class Product {

    @RequestMapping("/product/list")
    public String list(){return "product/list";};

    @RequestMapping("/product/add")
    public String add(){return "product/add";};

    @RequestMapping("/product/update")
    public String update(){return "product/update";};

    @RequestMapping("/product/delete")
    public String delete(){return "product/delete";};

}
