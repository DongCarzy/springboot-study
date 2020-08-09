package com.dxp.security.web.controller;

import com.dxp.security.web.controller.dto.Login;
import com.dxp.security.web.vo.LoginInfo;
import com.dxp.security.web.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统级别类接口
 *
 * @author carzy
 * @date 2020/8/6
 */
@RestController
@RequestMapping("/api/v1/")
@Api(tags = "系统", value = "系统登陆")
public class SystemController {

    @ApiOperation(value = "登录", notes = "系统登录接口")
    @PostMapping("/login")
    public R<LoginInfo> login(@RequestBody Login login) {
        // 无实质意义，只为生成swagger文档
        return R.suc(null);
    }

}
