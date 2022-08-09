package com.yasas.orderservice.util;

import com.yasas.orderservice.dto.OrderData;
import com.yasas.orderservice.entity.OrderEntity;

public class OrderObjectUtil {

    public static OrderEntity mapOrderDataToOrderEntity(OrderData orderData) {
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

    private static String getBidToOrderEntity(OrderData orderData) {
        return "id_" + orderData.getItemCode() + "_" + orderData.getUserName() + "_" + Long.toString(orderData.getOrderedDateTime());
    }
}
