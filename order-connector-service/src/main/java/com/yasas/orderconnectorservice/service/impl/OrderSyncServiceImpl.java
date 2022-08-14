package com.yasas.orderconnectorservice.service.impl;

import com.google.gson.Gson;
import com.yasas.orderconnectorservice.domain.OrderSyncRequest;
import com.yasas.orderconnectorservice.service.OrderSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderSyncServiceImpl implements OrderSyncService {

    @Value("${kafka.topic:order-listener}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderSyncServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void syncAndProduceOrderRequest(OrderSyncRequest orderSyncRequest) {
        Gson gson = new Gson();
        String order = gson.toJson(orderSyncRequest);
        if (validateOrderSyncRequest(orderSyncRequest)) {
            kafkaTemplate.send(topic, order);
        }
    }

    private boolean validateOrderSyncRequest(OrderSyncRequest orderSyncRequest) {
        if (orderSyncRequest.getUserName() == null || orderSyncRequest.getUserName().isBlank()) {
            return false;
        }
        if (orderSyncRequest.getEmail() == null || orderSyncRequest.getEmail().isBlank()) {
            return false;
        }
        if (orderSyncRequest.getItemCode() == null || orderSyncRequest.getItemCode().isBlank()) {
            return false;
        }
        if (orderSyncRequest.getOrderStatus() == null || orderSyncRequest.getOrderStatus().isBlank()) {
            return false;
        }
        return true;
    }
}
