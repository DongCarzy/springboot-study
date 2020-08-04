package com.dxp.security.conf;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security相关配置
 *
 * @author carzy
 * @date 2020/7/24
 */
@EnableWebSecurity
public class DxpSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/loginSuc")
                .and()
                .authorizeRequests()
                .antMatchers("/login_page").permitAll()
                .antMatchers("/product/add").hasRole("admin")
                .antMatchers("/product/list").hasAnyAuthority("product_list")
                .antMatchers("/product/update").hasAnyAuthority("product_update")
                .antMatchers("/product/delete").hasAnyAuthority("product_delete")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
