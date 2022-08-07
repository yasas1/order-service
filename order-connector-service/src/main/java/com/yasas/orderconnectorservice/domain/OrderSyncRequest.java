package com.yasas.orderconnectorservice.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderSyncRequest {
    private String orderStatus;

    private String userName;
    private String email;

    private String itemCategory;
    private String itemCode;
    private double price;

    private long orderedDateTime;

    private String countryCode;
    private String countryName;
    private String state;
    private String city;

    @Override
    public String toString() {
        return "OrderSyncRequest{" +
                "orderStatus='" + orderStatus + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", price=" + price +
                ", orderedDateTime=" + orderedDateTime +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
