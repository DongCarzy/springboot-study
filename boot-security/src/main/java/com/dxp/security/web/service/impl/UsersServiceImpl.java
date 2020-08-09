package com.dxp.security.web.service.impl;

import com.dxp.security.web.entity.sys.Authorization;
import com.dxp.security.web.entity.sys.Role;
import com.dxp.security.web.entity.sys.User;
import com.dxp.security.web.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号服务类
 *
 * @author carzy
 * @date 2020/8/4
 */
@Service
public class UsersServiceImpl implements UserDetailsService, UserServer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        //todo 访问数据库
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnabled(true);
        //todo 通过账号ID查角色表， 默认拥有 product 角色
        List<Role> list = new ArrayList<>();
        Role role = new Role();
        role.setId(1L);
        // 角色名不能以 ROLE_ 开头， 这是由于 security 框架限定的，用于区分角色和权限。 SecurityExpressionRoot 类中有用到，自己修改前缀
        role.setName("product");
        list.add(role);
        //todo.. 通过角色查询所有权限.  模拟有商品的查询，修改和保存权限，无删除权限
        List<Authorization> authorizations = new ArrayList<>();
        Authorization authorization = new Authorization();
        authorization.setId(1L);
        authorization.setName("product:read");
        authorizations.add(authorization);
        authorization = new Authorization();
        authorization.setId(2L);
        authorization.setName("product:update");
        authorizations.add(authorization);

        user.setRoles(list);
        user.setAuthorizations(authorizations);
        return user;
    }
}
