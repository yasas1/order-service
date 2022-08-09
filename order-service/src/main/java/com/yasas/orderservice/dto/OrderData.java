package com.yasas.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderData {

    private String orderStatus;

    private String userName;
    private String email;

    private String itemCode;
    private String itemCategory;
    private double price;
    private long orderedDateTime;

    private String countryCode;
    private String countryName;
    private String state;
    private String city;

}
