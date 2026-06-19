package com.groceryai.dto;

import lombok.Data;

@Data
public class AddItemRequest {

    private String name;
    private Double quantity;
    private String unit;
}