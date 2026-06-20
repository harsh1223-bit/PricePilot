package com.groceryai.dto;

import lombok.Data;

@Data
public class CreatePriceRequest {

    private Long productId;

    private Long storeId;

    private Double price;
}