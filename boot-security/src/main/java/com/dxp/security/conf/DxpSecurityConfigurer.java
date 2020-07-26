package com.dxp.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .and()
        .authorizeRequests()
                .antMatchers("/login_page", "/login").permitAll()
                .antMatchers("/product/add").hasAnyAuthority("product_add")
                .antMatchers("/product/list").hasAnyAuthority("product_list")
                .antMatchers("/product/update").hasAnyAuthority("product_update")
                .antMatchers("/product/delete").hasAnyAuthority("product_delete")
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123456").authorities("product_add","product_list")
        .and().passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html");
    }
}
