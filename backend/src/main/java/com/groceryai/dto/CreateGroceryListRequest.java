package com.groceryai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGroceryListRequest {

    @NotBlank
    private String title;
}