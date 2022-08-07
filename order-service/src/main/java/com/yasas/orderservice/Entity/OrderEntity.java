package com.yasas.orderservice.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class OrderEntity {

    @Id
    private Long tid;
    private String bid;
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

    private long createdDateTime;
    private long lastUpdatedDateTime;
}
