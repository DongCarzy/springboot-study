package com.dxp.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 3.0版本需要使用 @EnableOpenApi 注解, 而不是 @EnableSwagger2.
 *
 * DocumentationType 在3.0 中使用 DocumentationType.OAS_30
 * DocumentationType 在2.0 中使用 DocumentationType.SWAGGER_2
 *
 * @author carzy
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("v1")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dxp.security"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("为security测试接口")
                .contact(new Contact("carzy", "", "394163807@qq.com"))
                .version("1.0")
                .termsOfServiceUrl("http:localhost:8081")
                .build();
    }
}