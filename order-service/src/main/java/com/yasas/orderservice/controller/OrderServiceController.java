package com.yasas.orderservice.controller;

import com.yasas.orderservice.service.OrderService;
import com.yasas.orderservice.util.domain.HBM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/welcome")
    public Mono<String> welcomeOrderEvents(){
        return Mono.just("Welcome To Order Service");
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> streamOrders(){
        return Flux.interval(Duration.ofSeconds(10))
                .map(i-> (Object) HBM.INSTANCE)
                .mergeWith(i->orderService.streamOrders())
                .doOnError(Throwable::printStackTrace);
    }
}
