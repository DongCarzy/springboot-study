package com.dxp.shiro.web.service

import com.dxp.shiro.web.entity.sys.Role

/**
 * @author carzy
 */
interface RoleService {
    /**
     * 查询用户拥有的所有权限
     *
     * @param id 用户ID
     * @return 该用户拥有的所有角色
     */
    fun findAllByUserId(id: Long?): List<Role>

    /**
     * 查询当前登陆用户拥有的所有权限
     *
     * @return 该用户拥有的所有角色
     */
    fun findAllByConcurrentUser(): List<Role?>?
}