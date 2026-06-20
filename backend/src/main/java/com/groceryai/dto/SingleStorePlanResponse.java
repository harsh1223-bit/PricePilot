package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleStorePlanResponse {

    private Long storeId;

    private String storeName;

    private Double deliveryFee;

    private Double rating;

    private Double totalCost;
}