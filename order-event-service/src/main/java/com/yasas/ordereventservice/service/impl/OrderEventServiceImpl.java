package com.yasas.ordereventservice.service.impl;

import com.google.gson.Gson;
import com.yasas.ordereventservice.dto.OrderEventDto;
import com.yasas.ordereventservice.entity.OrderEvent;
import com.yasas.ordereventservice.repository.OrderEventRepository;
import com.yasas.ordereventservice.service.OrderEventService;
import com.yasas.ordereventservice.util.OrderEventObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class OrderEventServiceImpl implements OrderEventService {

    private static final String TOPIC = "order-listener";
    private static final String GROUP_ID = "orderGroup2";

    private final OrderEventRepository orderEventRepository;

    public OrderEventServiceImpl(OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
    }

    @KafkaListener(
            topics = TOPIC,
            groupId = GROUP_ID
    )
    public void consumeOrder(String eventMessage) {
        log.info("Order Message in order-event-service: " + eventMessage);
        Gson gson = new Gson();
        OrderEventDto orderData = gson.fromJson(eventMessage, OrderEventDto.class);
        log.info("OrderEventData in order-event-service: " + orderData);
        if (validateOrderData(orderData)) {
            this.createOrderEvent(OrderEventObjectUtil.mapOrderEventToOrderEventEntity(orderData)).subscribe();
        }
    }

    @Override
    public Mono<OrderEvent> createOrderEvent(OrderEvent orderEvent) {
        log.info("Order event to be saved : " + orderEvent);
        return orderEventRepository.save(orderEvent)
                .doOnError(throwable -> log.error("Error in saving : ", throwable));
    }

    private boolean validateOrderData(OrderEventDto orderDto) {
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
