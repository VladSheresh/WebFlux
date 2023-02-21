package com.example.WebFlux.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Greeting {

    private String message;

    @Override
    public String toString() {
        return "Greeting{" +
               "message='" + message + '\'' +
               '}';
    }
}
