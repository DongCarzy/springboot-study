package com.dxp.shiro.web.service

import com.dxp.shiro.web.entity.sys.User

/**
 * @author carzy
 */
interface UserService {
    /***
     * 通过账户名称号查找
     * @param name 账号名
     */
    fun findByUsername(name: String?): User?
}