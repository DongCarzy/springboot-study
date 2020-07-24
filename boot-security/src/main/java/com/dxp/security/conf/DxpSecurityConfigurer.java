package com.dxp.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security相关配置
 *
 * @author carzy
 * @date 2020/7/24
 */
@Configuration
@EnableWebSecurity
public class DxpSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * 设置密码加密函数.
     * BCryptPasswordEncoder: SHA-256 +随机盐+密钥对密码进行加密.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
