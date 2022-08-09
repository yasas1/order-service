package com.yasas.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {

//    @Autowired
//    private OrderService orderService;

    @GetMapping("/welcome")
    public Mono<String> welcomeOrderEvents(){
        return Mono.just("Welcome To Order Service");
    }
}
