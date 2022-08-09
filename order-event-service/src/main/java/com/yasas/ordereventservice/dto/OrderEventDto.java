package com.yasas.ordereventservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderEventDto {
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
