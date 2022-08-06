package com.yasas.orderconnectorservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order-connector")
public class OrderConnectorController {

    @PostMapping("/sync")
    public Mono<String> orderSync(){
        return Mono.just("test");
    }
}
