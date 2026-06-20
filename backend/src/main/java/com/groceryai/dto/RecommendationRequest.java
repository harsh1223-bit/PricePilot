package com.groceryai.dto;

import lombok.Data;

@Data
public class RecommendationRequest {

    private Double budget;
    private Integer familySize;
    private Integer days;
}