package com.yasas.orderconnectorservice.service;

import com.yasas.orderconnectorservice.domain.OrderSyncRequest;

public interface OrderSyncService {
    void syncAndProduceOrderRequest(OrderSyncRequest orderSyncRequest);
}
