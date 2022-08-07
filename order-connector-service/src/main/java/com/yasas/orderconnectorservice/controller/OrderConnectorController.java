package com.yasas.orderconnectorservice.controller;

import com.yasas.orderconnectorservice.domain.OrderSyncRequest;
import com.yasas.orderconnectorservice.service.OrderSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order-connector")
public class OrderConnectorController {

    @Autowired
    OrderSyncService orderSyncService;

    @PostMapping("/sync")
    public Mono<String> orderSync(@RequestBody OrderSyncRequest orderSyncRequest, ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        orderSyncService.syncAndProduceOrderRequest(orderSyncRequest);
        return Mono.just("Ok");
    }
}
