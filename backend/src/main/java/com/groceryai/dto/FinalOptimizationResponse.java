package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FinalOptimizationResponse {

    private SingleStorePlanResponse singleStorePlan;

    private Double singleStoreFinalCost;

    private Double multiStoreCost;

    private Double multiStoreFinalCost;

    private Double savings;

    private List<ProductRecommendationResponse> recommendations;
}