package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreComparisonResponse {
    private Integer rank;
    private String storeName;

    private Double rating;

    private Double deliveryFee;

    private Double totalCost;

    private List<String> pros;

    private List<String> cons;
}