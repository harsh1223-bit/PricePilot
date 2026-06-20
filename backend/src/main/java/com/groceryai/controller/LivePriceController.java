package com.groceryai.controller;

import com.groceryai.dto.LivePriceResponse;
import com.groceryai.service.LivePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/live-prices")
@RequiredArgsConstructor
public class LivePriceController {

    private final LivePriceService livePriceService;

    @GetMapping("/{productName}")
    public List<LivePriceResponse> getLivePrices(
            @PathVariable String productName
    ) {

        return livePriceService
                .getLivePrices(productName);
    }
}