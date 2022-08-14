package com.yasas.orderservice.util;

import com.yasas.orderservice.dto.OrderDto;
import com.yasas.orderservice.entity.OrderEntity;

public class OrderObjectUtil {

    public static OrderEntity mapOrderDataToOrderEntity(OrderDto orderData) {
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

    private static String getBidToOrderEntity(OrderDto orderDto) {
        return "id_" + orderDto.getItemCode() + "_" + orderDto.getUserName() + "_" + orderDto.getOrderedDateTime();
    }
}
