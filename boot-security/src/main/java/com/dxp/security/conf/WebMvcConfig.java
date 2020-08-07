package com.dxp.security.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * mvc配置
 *
 * @author carzy
 * @date 2020/8/6
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "DELETE", "POST", "PUT", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .exposedHeaders("D-TOKEN")
                .allowCredentials(true);
    }
}