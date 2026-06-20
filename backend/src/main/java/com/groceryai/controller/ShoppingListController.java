package com.groceryai.controller;

import com.groceryai.dto.*;
import com.groceryai.entity.ShoppingList;
import com.groceryai.entity.ShoppingListItem;
import com.groceryai.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-lists")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @PostMapping
    public ShoppingList createList(
            @RequestBody
            CreateShoppingListRequest request
    ) {

        return shoppingListService
                .createList(request);
    }

    @PostMapping("/{listId}/items")
    public ShoppingListItem addItem(
            @PathVariable Long listId,
            @RequestBody AddShoppingListItemRequest request
    ) {

        return shoppingListService
                .addItem(listId, request);
    }

    @GetMapping
    public List<ShoppingListResponse> getLists() {

        return shoppingListService
                .getLists();
    }

    @GetMapping("/{listId}/items")
    public List<ShoppingListItemResponse>
    getItems(
            @PathVariable Long listId
    ) {

        return shoppingListService
                .getItems(listId);
    }
}