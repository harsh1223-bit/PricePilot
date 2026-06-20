package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingListResponse {

    private Long id;

    private String name;
}