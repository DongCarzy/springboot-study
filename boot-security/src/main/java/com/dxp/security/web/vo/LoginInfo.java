package com.dxp.security.web.vo;

/**
 * 登录成功后返回的信息
 *
 * @author carzy
 * @date 2020/8/6
 */
public class LoginInfo {

    private String username;

    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
