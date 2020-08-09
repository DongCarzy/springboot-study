package com.dxp.security.conf.core;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * 权限
 *
 * @author dxp
 * 2020/8/9 4:48 下午
 */
public class StringGrantedAuthority implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role){
        Assert.isTrue(!role.startsWith("ROLE_"), () -> role + " cannot start with ROLE_ (it is automatically added)");
        this.name = "ROLE_" + role;
    }
}
