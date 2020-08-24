package com.dxp.shiro.web.service

import com.dxp.shiro.common.Constants
import com.dxp.shiro.web.entity.sys.Role
import com.dxp.shiro.web.entity.sys.User
import org.apache.shiro.SecurityUtils
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleServiceImpl : RoleService {

    override fun findAllByUserId(id: Long?): List<Role> {
        val roles: MutableList<Role> = ArrayList(1)
        val r = Role()
        r.id = 1
        r.name = "sa"
        roles.add(r)
        return roles
    }

    override fun findAllByConcurrentUser(): List<Role?>? {
        val session = SecurityUtils.getSubject().session
        val currentUser: User? = session.getAttribute(Constants.SESSION_USER_INFO) as User
        return if (currentUser != null) {
            findAllByUserId(currentUser.id)
        } else ArrayList()
    }
}