package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendedProductResponse {

    private String name;

    private Integer priority;

    private Boolean essential;

    private String reason;

    private Double price;
}