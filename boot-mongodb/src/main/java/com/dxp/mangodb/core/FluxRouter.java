package com.dxp.mangodb.core;

import com.dxp.mangodb.handler.UserFluxHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * 也可以采用传统的  @RestController @RequestMapping 的方式去定义路由.
 */
//@Configuration
public class FluxRouter {

    @Autowired
    private UserFluxHandler userFluxHandler;

//    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/users", RequestPredicates.accept(MediaType.APPLICATION_JSON), serverRequest -> userFluxHandler.listAll(serverRequest))
                .GET("/users/{id}", accept(MediaType.APPLICATION_JSON),serverRequest -> userFluxHandler.findOne(serverRequest))
                .POST("/users", accept(MediaType.APPLICATION_JSON),serverRequest -> userFluxHandler.save(serverRequest))
                .PUT("/users", accept(MediaType.APPLICATION_JSON),serverRequest -> userFluxHandler.update(serverRequest))
                .DELETE("/users", accept(MediaType.APPLICATION_JSON), serverRequest -> userFluxHandler.remove(serverRequest))
                .build();
    }
}
