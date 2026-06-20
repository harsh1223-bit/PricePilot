package com.groceryai.dto;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String name;
    private String category;
    private String brand;
}