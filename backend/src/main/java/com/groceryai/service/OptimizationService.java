package com.groceryai.service;

import com.groceryai.dto.FinalOptimizationResponse;
import com.groceryai.dto.ProductRecommendationResponse;
import com.groceryai.dto.SingleStorePlanResponse;
import com.groceryai.entity.Product;
import com.groceryai.entity.ProductPrice;
import com.groceryai.entity.Store;
import com.groceryai.repository.ProductPriceRepository;
import com.groceryai.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OptimizationService {

    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;

    public FinalOptimizationResponse optimize() {

        List<Product> products = productRepository.findAll();

        List<ProductRecommendationResponse> recommendations =
                new ArrayList<>();

        double multiStoreCost = 0.0;

        // Cheapest price for each product
        for (Product product : products) {

            ProductPrice cheapestPrice =
                    productPriceRepository
                            .findFirstByProductIdOrderByPriceAsc(
                                    product.getId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "No prices found for "
                                                    + product.getName()
                                    ));

            recommendations.add(
                    ProductRecommendationResponse.builder()
                            .productName(product.getName())
                            .storeName(
                                    cheapestPrice.getStore().getName()
                            )
                            .price(
                                    cheapestPrice.getPrice()
                            )
                            .build()
            );

            multiStoreCost += cheapestPrice.getPrice();
        }

        // Calculate total basket cost per store
        Map<String, Double> storeTotals =
                new HashMap<>();

        Map<String, Set<Long>> storeProducts =
                new HashMap<>();

        List<ProductPrice> allPrices =
                productPriceRepository.findAll();

        for (ProductPrice price : allPrices) {

            String storeName =
                    price.getStore().getName();

            storeTotals.put(
                    storeName,
                    storeTotals.getOrDefault(
                            storeName,
                            0.0
                    ) + price.getPrice()
            );

            storeProducts
                    .computeIfAbsent(
                            storeName,
                            k -> new HashSet<>()
                    )
                    .add(
                            price.getProduct().getId()
                    );
        }

        String bestStore = null;
        double lowestStoreCost =
                Double.MAX_VALUE;

        int totalProducts =
                products.size();

        for (String storeName :
                storeTotals.keySet()) {

            if (storeProducts
                    .get(storeName)
                    .size() != totalProducts) {
                continue;
            }

            double cost =
                    storeTotals.get(storeName);

            if (cost < lowestStoreCost) {
                lowestStoreCost = cost;
                bestStore = storeName;
            }
        }

        if (bestStore == null) {
            throw new RuntimeException(
                    "No store contains all products"
            );
        }

        Store bestStoreEntity = null;

        for (ProductPrice price : allPrices) {

            if (price.getStore()
                    .getName()
                    .equals(bestStore)) {

                bestStoreEntity =
                        price.getStore();

                break;
            }
        }

        if (bestStoreEntity == null) {
            throw new RuntimeException(
                    "Store not found"
            );
        }

        SingleStorePlanResponse singleStorePlan =
                SingleStorePlanResponse.builder()
                        .storeId(
                                bestStoreEntity.getId()
                        )
                        .storeName(
                                bestStoreEntity.getName()
                        )
                        .deliveryFee(
                                bestStoreEntity.getDeliveryFee()
                        )
                        .rating(
                                bestStoreEntity.getRating()
                        )
                        .totalCost(
                                lowestStoreCost
                        )
                        .build();

        // Single-store final cost
        double singleStoreFinalCost =
                lowestStoreCost;

        if (lowestStoreCost <
                bestStoreEntity
                        .getFreeDeliveryThreshold()) {

            singleStoreFinalCost +=
                    bestStoreEntity
                            .getDeliveryFee();
        }

        // Multi-store delivery calculation
        Set<Long> usedStoreIds =
                new HashSet<>();

        for (ProductRecommendationResponse recommendation
                : recommendations) {

            for (ProductPrice price :
                    allPrices) {

                if (price.getProduct()
                        .getName()
                        .equals(
                                recommendation
                                        .getProductName()
                        )
                        &&
                        price.getStore()
                                .getName()
                                .equals(
                                        recommendation
                                                .getStoreName()
                                )) {

                    usedStoreIds.add(
                            price.getStore()
                                    .getId()
                    );

                    break;
                }
            }
        }

        double multiStoreDeliveryCost =
                0.0;

        for (Long storeId :
                usedStoreIds) {

            for (ProductPrice price :
                    allPrices) {

                if (price.getStore()
                        .getId()
                        .equals(storeId)) {

                    multiStoreDeliveryCost +=
                            price.getStore()
                                    .getDeliveryFee();

                    break;
                }
            }
        }

        double multiStoreFinalCost =
                multiStoreCost +
                        multiStoreDeliveryCost;

        return FinalOptimizationResponse
                .builder()
                .singleStorePlan(
                        singleStorePlan
                )
                .singleStoreFinalCost(
                        singleStoreFinalCost
                )
                .multiStoreCost(
                        multiStoreCost
                )
                .multiStoreFinalCost(
                        multiStoreFinalCost
                )
                .savings(
                        multiStoreFinalCost
                                - singleStoreFinalCost
                )
                .recommendations(
                        recommendations
                )
                .build();
    }
}