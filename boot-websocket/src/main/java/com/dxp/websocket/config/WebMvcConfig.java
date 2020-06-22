package com.dxp.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * mvc 配置
 *
 * @author carzy
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 静态资源处理
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
    }

    /**
     * 跨域配置
     *
     * @param registry CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "DELETE", "POST", "PUT", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}