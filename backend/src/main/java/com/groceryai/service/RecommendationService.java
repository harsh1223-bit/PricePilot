package com.groceryai.service;

import com.groceryai.dto.RecommendationRequest;
import com.groceryai.dto.RecommendationResponse;
import com.groceryai.entity.Product;
import com.groceryai.entity.ProductPrice;
import com.groceryai.repository.ProductPriceRepository;
import com.groceryai.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;

    public RecommendationResponse recommend(
            RecommendationRequest request
    ) {

        List<Product> products =
                productRepository.findAll();

        products.sort(
                Comparator.comparing(
                        Product::getPriorityScore,
                        Comparator.nullsLast(
                                Comparator.reverseOrder()
                        )
                )
        );

        List<String> recommendedProducts =
                new ArrayList<>();

        double totalCost = 0.0;

        for (Product product : products) {

            ProductPrice cheapestPrice =
                    productPriceRepository
                            .findFirstByProductIdOrderByPriceAsc(
                                    product.getId()
                            )
                            .orElse(null);

            if (cheapestPrice == null) {
                continue;
            }

            double price =
                    cheapestPrice.getPrice();

            if (totalCost + price
                    <= request.getBudget()) {

                recommendedProducts.add(
                        product.getName()
                );

                totalCost += price;
            }
        }

        return RecommendationResponse.builder()
                .budget(request.getBudget())
                .estimatedCost(totalCost)
                .recommendedProducts(
                        recommendedProducts
                )
                .build();
    }
}