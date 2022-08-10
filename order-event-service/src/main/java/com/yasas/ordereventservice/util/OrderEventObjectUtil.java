package com.yasas.ordereventservice.util;

import com.yasas.ordereventservice.dto.OrderEventDto;
import com.yasas.ordereventservice.entity.OrderEvent;

public class OrderEventObjectUtil {

    public static OrderEvent mapOrderEventToOrderEventEntity(OrderEventDto orderEventDto) {
        return OrderEvent.builder()
                .bid(getBidToOrderEntity(orderEventDto))
                .orderStatus(orderEventDto.getOrderStatus())
                .userName(orderEventDto.getUserName())
                .email(orderEventDto.getEmail())
                .itemCode(orderEventDto.getItemCode())
                .itemCategory(orderEventDto.getItemCategory())
                .price(orderEventDto.getPrice())
                .orderedDateTime(orderEventDto.getOrderedDateTime())
                .countryCode(orderEventDto.getCountryCode())
                .countryName(orderEventDto.getCountryName())
                .state(orderEventDto.getState())
                .city(orderEventDto.getCity())
                .createdDateTime(System.currentTimeMillis())
                .build();
    }

    private static String getBidToOrderEntity(OrderEventDto orderEventDto) {
        return "id_" + orderEventDto.getItemCode() + "_" + orderEventDto.getUserName() + "_" + orderEventDto.getOrderStatus();
    }
}
