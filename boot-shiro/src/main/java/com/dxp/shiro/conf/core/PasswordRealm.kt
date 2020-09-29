package com.dxp.shiro.conf.core

import com.dxp.shiro.common.Constants
import com.dxp.shiro.web.entity.sys.Permit
import com.dxp.shiro.web.entity.sys.Role
import com.dxp.shiro.web.entity.sys.User
import com.dxp.shiro.web.service.PermitService
import com.dxp.shiro.web.service.RoleService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.*
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import java.util.*
import java.util.stream.Collectors

/**
 * @author carzy.
 * @date 15:02 2018/12/25
 */
class PasswordRealm(private val roleService: RoleService, private val permitService: PermitService) : AuthorizingRealm() {

    /**
     * 限定只用 UsernamePasswordToken
     */
    override fun getAuthenticationTokenClass(): Class<*> {
        return UsernamePasswordToken::class.java
    }

    /**
     * 这里只做授权
     */
    override fun doGetAuthorizationInfo(principalCollection: PrincipalCollection): AuthorizationInfo {
        val authorizationInfo = SimpleAuthorizationInfo()
        val session = SecurityUtils.getSubject().session
        //查询用户的权限
        var roles: Set<String?> = HashSet()
        var permission: List<String?> = ArrayList()
        val r = session.getAttribute(Constants.SESSION_USER_ROLE)
        if (r is Set<*>) {
            roles = r as Set<String?>
        }
        val p = session.getAttribute(Constants.SESSION_USER_PERMISSION)
        if (p is List<*>) {
            permission = p as List<String?>
        }
        //为当前用户设置角色和权限
        authorizationInfo.addStringPermissions(permission)
        authorizationInfo.roles = roles
        return authorizationInfo
    }

    /**
     * 登陆认证
     *
     * @param authenticationToken AuthenticationToken
     * @return 认证信息
     * @throws AuthenticationException 认证失败
     */
    @Throws(AuthenticationException::class)
    override fun doGetAuthenticationInfo(authenticationToken: AuthenticationToken): AuthenticationInfo? {
        if (authenticationToken !is UsernamePasswordToken) {
            return null
        }
        if (null == authenticationToken.getPrincipal() || null == authenticationToken.getCredentials()) {
            throw UnknownAccountException()
        }
        val session = SecurityUtils.getSubject().session
        val currentUser = session.getAttribute(Constants.SESSION_USER_INFO) as User

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        val authenticationInfo = SimpleAuthenticationInfo(
                currentUser.username,
                currentUser.password,
                name
        )
        currentUser.password = ""

        //将用户信息放入session中
        session.setAttribute(Constants.SESSION_USER_INFO, currentUser)
        val roles = roleService.findAllByUserId(currentUser.id)
        val permits: List<Permit>

        // 含有sa角色， 则赋予全部权限
        val set: Set<Role> = roles.stream().filter { r: Role? -> "sa" == r!!.name }.collect(Collectors.toSet())
        permits = if (set.isNotEmpty()) {
            permitService.findAll()
        } else {
            permitService.findAllByUserId(currentUser.id)
        }
        session.setAttribute(Constants.SESSION_USER_ROLE, roles.stream().map<String>(Role::name).collect(Collectors.toSet()))
        session.setAttribute(Constants.SESSION_USER_PERMISSION, permits.stream().map(Permit::name).collect(Collectors.toList()))
        return authenticationInfo
    }
}