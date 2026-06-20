package com.groceryai.service;

import com.groceryai.dto.CreateProductRequest;
import com.groceryai.dto.ProductResponse;
import com.groceryai.entity.Product;
import com.groceryai.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(
            CreateProductRequest request
    ) {

        Product product = Product.builder()
                .name(request.getName())
                .category(request.getCategory())
                .brand(request.getBrand())
                .build();

        Product savedProduct =
                productRepository.save(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .category(savedProduct.getCategory())
                .brand(savedProduct.getBrand())
                .priorityScore(savedProduct.getPriorityScore())
                .essential(savedProduct.getEssential())
                .build();
    }

    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(product ->
                        ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .category(product.getCategory())
                                .brand(product.getBrand())
                                .priorityScore(product.getPriorityScore())
                                .essential(product.getEssential())
                                .build()
                )
                .collect(java.util.stream.Collectors.toList());
    }
}