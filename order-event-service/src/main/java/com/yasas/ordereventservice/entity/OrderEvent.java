package com.yasas.ordereventservice.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Document(collation = "OrderEvent")
public class OrderEvent {

    @Id
    private String id;
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
