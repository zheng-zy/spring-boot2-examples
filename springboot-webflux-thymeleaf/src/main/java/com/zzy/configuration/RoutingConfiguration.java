package com.zzy.configuration;

import com.zzy.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/10/11.
 */
@Configuration
public class RoutingConfiguration {
    @Bean
    public RouterFunction<ServerResponse> userRouterHandler(UserHandler userHandler) {
        return route(GET("/user/{userId}").and(accept(APPLICATION_JSON)), userHandler::getUser)
                .andRoute(POST("/getUserByJson").and(accept(APPLICATION_JSON)), userHandler::getUserByJson)
                .andRoute(GET("/getUserObj/{userId}").and(accept(APPLICATION_JSON)), userHandler::getUserObj)
                .andRoute(GET("/users").and(accept(APPLICATION_JSON)), userHandler::getUsers)
                .andRoute(DELETE("/user/{user}").and(accept(APPLICATION_JSON)), userHandler::deleteUser)
                .andRoute(GET("/hi").and(accept(APPLICATION_JSON)), userHandler::Hi);
    }

//    @Bean
    public RouterFunction<ServerResponse> timeHandler(UserHandler userHandler) {
        return route(GET("/time/{user}").and(accept(APPLICATION_JSON)), userHandler::getUserObj);
    }
}
