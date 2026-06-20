package com.groceryai.service;

import com.groceryai.dto.CreatePriceRequest;
import com.groceryai.dto.PriceResponse;
import com.groceryai.entity.Product;
import com.groceryai.entity.ProductPrice;
import com.groceryai.entity.Store;
import com.groceryai.repository.ProductPriceRepository;
import com.groceryai.repository.ProductRepository;
import com.groceryai.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductPrice create(CreatePriceRequest request) {

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Store store = storeRepository
                .findById(request.getStoreId())
                .orElseThrow(() ->
                        new RuntimeException("Store not found"));

        ProductPrice productPrice = ProductPrice.builder()
                .price(request.getPrice())
                .product(product)
                .store(store)
                .build();

        return productPriceRepository.save(productPrice);
    }

    public List<PriceResponse> getPricesByProduct(Long productId) {

        return productPriceRepository
                .findByProductId(productId)
                .stream()
                .map(price -> PriceResponse.builder()
                        .id(price.getId())
                        .storeName(price.getStore().getName())
                        .price(price.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}