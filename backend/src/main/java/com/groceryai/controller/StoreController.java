package com.groceryai.controller;

import com.groceryai.dto.CreateStoreRequest;
import com.groceryai.dto.StoreResponse;
import com.groceryai.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public StoreResponse create(
            @RequestBody CreateStoreRequest request
    ) {

        return storeService.create(request);
    }

    @GetMapping
    public List<StoreResponse> getAll() {

        return storeService.getAll();
    }
}