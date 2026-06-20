package com.groceryai.controller;

import com.groceryai.dto.FinalOptimizationResponse;
import com.groceryai.service.OptimizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optimize")
@RequiredArgsConstructor
public class OptimizationController {

    private final OptimizationService optimizationService;

    @GetMapping
    public FinalOptimizationResponse optimize() {
        return optimizationService.optimize();
    }
}