package com.example.WebFlux.config;

import com.example.WebFlux.handler.GreetingHandler;
import com.example.WebFlux.entity.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        return RouterFunctions
                .route(GET("/hello")
                        .and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello)
                .andRoute(GET("/"),
                        ServerRequest -> {
                            String name = ServerRequest.queryParam("name").orElse("null");
                            return ServerResponse
                                    .ok()
                                    .body(
                                            BodyInserters.fromValue(new Greeting("Hello, " + name))
                                    );
                        }
                )
                .andRoute(GET("/list"), greetingHandler::listName);
    }
}