package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecommendationResponse {

    private Double budget;

    private Double estimatedCost;

    private List<RecommendedProductResponse> recommendedProducts;
}