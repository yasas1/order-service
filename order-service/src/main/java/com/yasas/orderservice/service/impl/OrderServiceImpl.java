package com.yasas.orderservice.service.impl;

import com.google.gson.Gson;
import com.yasas.orderservice.repository.OrderRepository;
import com.yasas.orderservice.dto.OrderDto;
import com.yasas.orderservice.entity.OrderEntity;
import com.yasas.orderservice.service.OrderService;
import com.yasas.orderservice.util.OrderObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private static final String TOPIC = "order-listener";
    private static final String GROUP_ID = "orderGroup1";

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(
            topics = TOPIC,
            groupId = GROUP_ID
    )
    public void consumeOrder(String eventMessage) {
        log.info("Order Message in order-service: " + eventMessage);
        Gson gson = new Gson();
        OrderDto orderData = gson.fromJson(eventMessage, OrderDto.class);
        log.info("OrderData in order-service: " + orderData);
        if (validateOrderData(orderData)) {
            this.createOrUpdateOrder(OrderObjectUtil.mapOrderDataToOrderEntity(orderData)).subscribe();
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
                    return orderRepository.save(orderEntity)
                            .doOnError(throwable -> log.error("Error in update order : ", throwable));
                })
                .switchIfEmpty(Mono.defer(() -> orderRepository.save(orderEntity)))
                .doOnError(throwable -> log.error("Error in save order : ", throwable));
    }

    @Override
    public Flux<OrderEntity> streamOrders() {
        return orderRepository.findAll();
    }

    private boolean validateOrderData(OrderDto orderDto) {
        if (orderDto.getUserName() == null || orderDto.getUserName().isBlank()) {
            return false;
        }
        if (orderDto.getEmail() == null || orderDto.getEmail().isBlank()) {
            return false;
        }
        if (orderDto.getItemCode() == null || orderDto.getItemCode().isBlank()) {
            return false;
        }
        if (orderDto.getOrderStatus() == null || orderDto.getOrderStatus().isBlank()) {
            return false;
        }
        if (orderDto.getOrderedDateTime() == 0) {
            orderDto.setOrderedDateTime(System.currentTimeMillis());
        }
        return true;
    }

}
