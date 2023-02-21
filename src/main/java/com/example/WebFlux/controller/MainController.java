package com.example.WebFlux.controller;

import com.example.WebFlux.entity.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class MainController {

    @GetMapping
    public Flux<Greeting> test (@RequestParam(defaultValue = "null") String name) {
        return Flux.just("Hello, Spring!" + name, name + "Hello, Spring!")
                .sort()
                .map(Greeting::new);
    }
}
