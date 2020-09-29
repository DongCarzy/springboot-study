package com.dxp.shiro.web.service

import com.dxp.shiro.web.entity.sys.Permit

/**
 * 权限
 *
 * @author carzy
 * @date 2020/8/24
 */
interface PermitService {

    /**
     * 查询所有权限
     */
    fun findAll(): List<Permit>;

    /**
     * 查询某一用户的所有权限。
     */
    fun findAllByUserId(id: Long?): List<Permit>;
}