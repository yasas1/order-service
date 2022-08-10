package com.yasas.orderservice.service;

import com.yasas.orderservice.entity.OrderEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OrderEntity> createOrUpdateOrder(OrderEntity orderEntity);

    Flux<OrderEntity> streamOrders();
}
