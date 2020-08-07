package com.dxp.security.web.controller.dto;

import io.swagger.annotations.ApiModel;

/**
 * @author carzy
 * @date 2020/8/6
 */
@ApiModel
public class Login {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
