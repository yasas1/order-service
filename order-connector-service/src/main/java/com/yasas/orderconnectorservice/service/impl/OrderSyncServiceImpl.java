package com.yasas.orderconnectorservice.service.impl;

import com.google.gson.Gson;
import com.yasas.orderconnectorservice.domain.OrderSyncRequest;
import com.yasas.orderconnectorservice.service.OrderSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderSyncServiceImpl implements OrderSyncService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public void syncAndProduceOrderRequest(OrderSyncRequest orderSyncRequest) {
        Gson gson = new Gson();
        String order = gson.toJson(orderSyncRequest);
        kafkaTemplate.send("order-listener",order);
    }
}
