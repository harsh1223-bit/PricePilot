package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingListItemResponse {

    private Long productId;

    private String productName;

    private Integer quantity;
}