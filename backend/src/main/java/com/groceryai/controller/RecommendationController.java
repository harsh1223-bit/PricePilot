package com.groceryai.controller;

import com.groceryai.dto.RecommendationRequest;
import com.groceryai.dto.RecommendationResponse;
import com.groceryai.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping
    public RecommendationResponse recommend(
            @RequestBody RecommendationRequest request
    ) {

        return recommendationService
                .recommend(request);
    }
}