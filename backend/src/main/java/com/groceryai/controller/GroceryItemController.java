package com.groceryai.controller;

import com.groceryai.dto.AddItemRequest;
import com.groceryai.entity.GroceryItem;
import com.groceryai.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class GroceryItemController {

    private final GroceryItemService groceryItemService;

    @PostMapping("/{listId}")
    public GroceryItem addItem(
            @PathVariable Long listId,
            @RequestBody AddItemRequest request
    ) {
        return groceryItemService.addItem(listId, request);
    }
}