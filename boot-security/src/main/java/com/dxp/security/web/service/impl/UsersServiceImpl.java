package com.dxp.security.web.service.impl;

import com.dxp.security.web.entity.sys.Role;
import com.dxp.security.web.entity.sys.User;
import com.dxp.security.web.service.UserServer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        //todo..  访问数据库
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnabled(true);
        // 通过账号ID查角色表
        List<Role> list = new ArrayList<>();
        Role role = new Role();
        role.setId(1L);
        role.setName("admin");
        list.add(role);
        user.setAuthorities(list);
        return user;
    }
}
