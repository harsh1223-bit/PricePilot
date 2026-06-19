package com.groceryai.controller;

import com.groceryai.dto.CreateGroceryListRequest;
import com.groceryai.entity.GroceryList;
import com.groceryai.service.GroceryListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@RequiredArgsConstructor
public class GroceryListController {

    private final GroceryListService groceryListService;

    @PostMapping
    public GroceryList create(
            @Valid @RequestBody CreateGroceryListRequest request
    ) {
        return groceryListService.create(request);
    }

    @GetMapping
    public List<GroceryList> getAll() {
        return groceryListService.getAll();
    }

    @GetMapping("/{id}")
    public GroceryList getById(@PathVariable Long id) {
        return groceryListService.getById(id);
    }
}