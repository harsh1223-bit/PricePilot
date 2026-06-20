package com.groceryai.controller;

import com.groceryai.dto.CreatePriceRequest;
import com.groceryai.dto.PriceResponse;
import com.groceryai.entity.ProductPrice;
import com.groceryai.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    @PostMapping
    public ProductPrice create(
            @RequestBody CreatePriceRequest request
    ) {
        return productPriceService.create(request);
    }

    @GetMapping("/product/{productId}")
    public List<PriceResponse> getPrices(
            @PathVariable Long productId
    ) {
        return productPriceService
                .getPricesByProduct(productId);
    }
}