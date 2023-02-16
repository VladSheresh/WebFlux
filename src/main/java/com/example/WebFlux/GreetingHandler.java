package com.example.WebFlux;

import com.example.WebFlux.entity.Greeting;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        BodyInserter<Greeting, ReactiveHttpOutputMessage> body =
                BodyInserters.fromValue(new Greeting("Hello, Spring!"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}
