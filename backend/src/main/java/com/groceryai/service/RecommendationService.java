package com.groceryai.service;

import com.groceryai.dto.RecommendationRequest;
import com.groceryai.dto.RecommendationResponse;
import com.groceryai.dto.RecommendedProductResponse;
import com.groceryai.entity.Product;
import com.groceryai.entity.ProductPrice;
import com.groceryai.repository.ProductPriceRepository;
import com.groceryai.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.groceryai.dto.RecommendedProductResponse;
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

        List<RecommendedProductResponse> recommendedProducts =
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

                String reason;

                if (Boolean.TRUE.equals(product.getEssential())) {
                    reason = "Essential household item";
                } else {
                    reason = "Recommended within budget";
                }

                recommendedProducts.add(
                        RecommendedProductResponse.builder()
                                .name(product.getName())
                                .priority(product.getPriorityScore())
                                .essential(product.getEssential())
                                .reason(reason)
                                .price(price)
                                .build()
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