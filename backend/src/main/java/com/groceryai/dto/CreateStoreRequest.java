package com.groceryai.dto;

import lombok.Data;

@Data
public class CreateStoreRequest {

    private String name;

    private Double deliveryFee;

    private Double rating;

    private String address;
    private Double freeDeliveryThreshold;
}