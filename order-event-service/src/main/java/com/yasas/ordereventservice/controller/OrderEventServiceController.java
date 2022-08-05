package com.yasas.ordereventservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order-events")
public class OrderEventServiceController {

    @GetMapping("/welcome")
    public Mono<String> welcomeOrderEvents(){
        return Mono.just("Welcome To Order Events Service");
    }
}
