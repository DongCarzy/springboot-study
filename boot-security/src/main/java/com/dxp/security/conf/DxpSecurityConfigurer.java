package com.dxp.security.conf;

import com.dxp.security.conf.core.AjaxLoginFilter;
import com.dxp.security.conf.core.SucLoginHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security相关配置
 *
 * @author carzy
 * @date 2020/7/24
 */
@EnableWebSecurity
public class DxpSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * 需要放行的URL
     */
    private static final String[] AUTH_WHITELIST = {
            // -- register url
//            "/api/v1/login",
            // -- swagger ui
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**"
            // other public endpoints of your API may be appended to this array
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginProcessingUrl("/api/v1/from/login")
                .successForwardUrl("/api/v1/from/login")
                .successHandler(new SucLoginHandler())
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/product/add").hasRole("admin")
                .antMatchers("/product/list").hasAnyAuthority("product_list")
                .antMatchers("/product/update").hasAnyAuthority("product_update")
                .antMatchers("/product/delete").hasAnyAuthority("product_delete")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        AjaxLoginFilter filter = new AjaxLoginFilter();
        filter.setAuthenticationManager(authenticationManager());
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
