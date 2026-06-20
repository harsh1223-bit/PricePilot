package com.groceryai.dto;

import lombok.Data;

@Data
public class AddShoppingListItemRequest {

    private Long productId;

    private Integer quantity;
}