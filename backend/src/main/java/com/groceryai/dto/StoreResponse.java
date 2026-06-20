package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {

    private Long id;

    private String name;

    private Double deliveryFee;

    private Double rating;

    private String address;
    private Double freeDeliveryThreshold;
}