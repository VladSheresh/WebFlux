package com.example.WebFlux.controller;

import com.example.WebFlux.entity.Greeting;
import com.example.WebFlux.entity.Message;
import com.example.WebFlux.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
@AllArgsConstructor
public class MainController {

    private final MessageService messageService;

//    @GetMapping
//    public Flux<Greeting> test (@RequestParam(defaultValue = "null") String name) {
//        return Flux.just("Hello, Spring!" + name, name + "Hello, Spring!")
//                .sort()
//                .map(Greeting::new);
//    }

    @GetMapping
    public Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "3") Long count
    ) {
        return messageService.list();
    }

    @PostMapping
    public Mono<Message> add(@RequestBody Message message) {
        return messageService.addOne(message);
    }

}
