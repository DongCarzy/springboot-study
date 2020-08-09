package com.dxp.security.common;

import com.dxp.security.conf.core.StringGrantedAuthority;
import com.dxp.security.web.entity.sys.Role;
import com.dxp.security.web.entity.sys.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * 生成Token
 */
public class JWTTokenUtil {

    // 1个小时候过期
    private static final long express = 3600 * 1000;
    private static final String secret = "dxp_20200809";

    /**
     * @param user 用户信息
     * @return token
     */
    public static String createAccessToken(User user){
        // 登陆成功生成JWT
        return Jwts.builder()
                // 放入用户名和用户ID
                .setId(user.getId()+"")
                // 主题
                .setSubject(user.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("dxp")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", user.getAuthorities().stream().map(StringGrantedAuthority::getName).collect(Collectors.joining(",")))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + express))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}