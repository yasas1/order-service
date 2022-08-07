package com.yasas.orderservice.service.impl;

import com.yasas.orderservice.Entity.OrderEntity;
import com.yasas.orderservice.Repository.OrderRepository;
import com.yasas.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity createOrUpdateOrder(OrderEntity orderEntity) {
        return null;
    }
}
