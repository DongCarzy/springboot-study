package com.dxp.security.web.entity.sys;

import org.springframework.security.core.GrantedAuthority;

/**
 * 账号角色
 *
 * @author carzy
 * @date 2020/8/4
 */
public class Role implements GrantedAuthority {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
