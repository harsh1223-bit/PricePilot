package com.groceryai.controller;

import com.groceryai.dto.CreateProductRequest;
import com.groceryai.dto.ProductResponse;
import com.groceryai.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse create(
            @RequestBody CreateProductRequest request
    ) {
        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }
}