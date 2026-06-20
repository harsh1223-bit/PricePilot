package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LivePriceResponse {

    private String storeName;
    private Double price;
}