package com.yasas.ordereventservice.service;

import com.yasas.ordereventservice.entity.OrderEvent;
import reactor.core.publisher.Mono;

public interface OrderEventService {

    Mono<OrderEvent> createOrderEvent(OrderEvent orderEvent);
}
