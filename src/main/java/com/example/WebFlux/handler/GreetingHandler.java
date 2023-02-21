package com.example.WebFlux.handler;

import com.example.WebFlux.entity.Greeting;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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

    public Mono<ServerResponse> listName(ServerRequest request) {
        String name = request.queryParam("name").orElse("null");
        Long start = request.queryParam("start").map(Long::valueOf).orElse(0L);
        Long count = request.queryParam("count").map(Long::valueOf).orElse(2L);

        Flux<Greeting> data = Flux.just(
                1 + name,
                2 + name,
                3 + name,
                4 + name,
                5 + name,
                6 + name,
                7 + name)
                .skip(start)
                .take(count)
                .map(Greeting::new);

        return ServerResponse.ok()
                .body(data, Greeting.class);
    }
}
