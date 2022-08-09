package com.yasas.ordereventservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "order-event")
public class OrderEvent {

    @Id
    private String tid;
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
}
