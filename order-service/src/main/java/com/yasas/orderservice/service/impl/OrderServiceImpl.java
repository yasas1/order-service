package com.yasas.orderservice.service.impl;

import com.yasas.orderservice.Entity.OrderEntity;
import com.yasas.orderservice.Repository.OrderRepository;
import com.yasas.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(
            topics = "order-listener",
            groupId = "orderGroup"
    )
    public void consumeOrder(String eventMessage) {
        log.info("Order Message : " + eventMessage);
    }

    @Override
    public OrderEntity createOrUpdateOrder(OrderEntity orderEntity) {
        return null;
    }
}
