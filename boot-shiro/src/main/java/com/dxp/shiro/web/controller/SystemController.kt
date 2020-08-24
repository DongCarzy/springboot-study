package com.dxp.shiro.web.controller

import com.dxp.shiro.common.Constants
import com.dxp.shiro.web.controller.dto.Login
import com.dxp.shiro.web.entity.sys.User
import com.dxp.shiro.web.service.UserService
import com.dxp.shiro.web.vo.LoginInfo
import com.dxp.shiro.web.vo.R
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Md5Hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 系统级别类接口
 *
 * @author carzy
 * @date 2020/8/6
 */
@RestController
@RequestMapping("/api/v1/")
@Api(tags = ["系统"], value = "系统登陆")
class SystemController {

    @Autowired
    private lateinit var userService: UserService;

    @ApiOperation(value = "登录", notes = "系统登录接口")
    @PostMapping("/login")
    fun login(@RequestBody login: Login): R<LoginInfo> {

        val user: User? = this.userService.findByUsername(login.username)
        if (user == null || !user.password.equals(Md5Hash(login.password, null, Constants.MD5_HASH_ITERATIONS).toString())) {
            throw UnknownAccountException()
        }

        val currentUser = SecurityUtils.getSubject()
        val session = currentUser.session
        session.setAttribute(Constants.SESSION_USER_INFO, user)
        val token = UsernamePasswordToken(login.username, login.password)
        synchronized(SystemController::class.java) {
            currentUser.login(token)
        }
        val loginInfo = LoginInfo()
        loginInfo.username = user.username
        loginInfo.token = session.id as String
        return R.suc(loginInfo)
    }


}