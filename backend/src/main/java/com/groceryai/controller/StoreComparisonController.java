package com.groceryai.controller;

import com.groceryai.dto.StoreComparisonResponse;
import com.groceryai.service.StoreComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreComparisonController {

    private final StoreComparisonService storeComparisonService;

    @GetMapping("/compare")
    public List<StoreComparisonResponse> compareStores() {

        return storeComparisonService.compareStores();
    }
}