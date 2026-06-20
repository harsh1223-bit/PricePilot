package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRecommendationResponse {

    private String productName;

    private String storeName;

    private Double price;
}