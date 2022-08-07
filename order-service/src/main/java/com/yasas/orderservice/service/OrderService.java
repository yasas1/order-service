package com.yasas.orderservice.service;

import com.yasas.orderservice.Entity.OrderEntity;

public interface OrderService {

    OrderEntity createOrUpdateOrder(OrderEntity orderEntity);
}
