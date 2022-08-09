package com.yasas.orderservice.service.impl;

import com.google.gson.Gson;
import com.yasas.orderservice.repository.OrderRepository;
import com.yasas.orderservice.entity.OrderData;
import com.yasas.orderservice.entity.OrderEntity;
import com.yasas.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private static final String TOPIC = "order-listener";
    private static final String GROUP_ID = "orderGroup";

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(
            topics = TOPIC,
            groupId = GROUP_ID
    )
    public void consumeOrder(String eventMessage) {
        log.info("Order Message : " + eventMessage);
        Gson gson = new Gson();
        OrderData orderData = gson.fromJson(eventMessage, OrderData.class);
        log.info("OrderData : " + orderData);
        if (validateOrderData(orderData)) {
            this.createOrUpdateOrder(mapOrderDataToOrderEntity(orderData)).subscribe();
        }
    }

    @Override
    public Mono<OrderEntity> createOrUpdateOrder(OrderEntity orderEntity) {
        return orderRepository.findByBId(orderEntity.getBid())
                .flatMap(exist -> {
                    log.info("Order found : " + exist);
                    orderEntity.setTid(exist.getTid());
                    orderEntity.setCreatedDateTime(exist.getCreatedDateTime());
                    orderEntity.setLastUpdatedDateTime(System.currentTimeMillis());
                    return orderRepository.save(orderEntity);
                })
                .switchIfEmpty(Mono.defer(() -> orderRepository.save(orderEntity)))
                .doOnError(throwable -> log.error("Error in create or update order : ", throwable));
    }

    private boolean validateOrderData(OrderData orderData) {
        if (orderData.getUserName() == null || orderData.getUserName().isBlank()) {
            return false;
        }
        if (orderData.getEmail() == null || orderData.getEmail().isBlank()) {
            return false;
        }
        if (orderData.getItemCode() == null || orderData.getItemCode().isBlank()) {
            return false;
        }
        if (orderData.getOrderedDateTime() == 0) {
            orderData.setOrderedDateTime(System.currentTimeMillis());
        }
        return true;
    }

    private OrderEntity mapOrderDataToOrderEntity(OrderData orderData) {
        return OrderEntity.builder()
                .bid(getBidToOrderEntity(orderData))
                .orderStatus(orderData.getOrderStatus())
                .userName(orderData.getUserName())
                .email(orderData.getEmail())
                .itemCode(orderData.getItemCode())
                .itemCategory(orderData.getItemCategory())
                .price(orderData.getPrice())
                .orderedDateTime(orderData.getOrderedDateTime())
                .countryCode(orderData.getCountryCode())
                .countryName(orderData.getCountryName())
                .state(orderData.getState())
                .city(orderData.getCity())
                .createdDateTime(System.currentTimeMillis())
                .lastUpdatedDateTime(System.currentTimeMillis())
                .build();
    }

    private String getBidToOrderEntity(OrderData orderData) {
        return "id_" + orderData.getItemCode() + "_" + orderData.getUserName() + "_" + Long.toString(orderData.getOrderedDateTime());
    }
}