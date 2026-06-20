package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String category;
    private String brand;

    private Integer priorityScore;
    private Boolean essential;
}