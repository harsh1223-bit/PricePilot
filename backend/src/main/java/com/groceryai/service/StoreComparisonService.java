package com.groceryai.service;

import com.groceryai.dto.StoreComparisonResponse;
import com.groceryai.entity.ProductPrice;
import com.groceryai.entity.Store;
import com.groceryai.repository.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StoreComparisonService {

    private final ProductPriceRepository productPriceRepository;

    public List<StoreComparisonResponse> compareStores() {

        List<ProductPrice> allPrices =
                productPriceRepository.findAll();

        Map<Long, Double> storeTotals =
                new HashMap<>();

        Map<Long, Store> stores =
                new HashMap<>();

        for (ProductPrice price : allPrices) {

            Store store = price.getStore();

            stores.put(store.getId(), store);

            storeTotals.put(
                    store.getId(),
                    storeTotals.getOrDefault(
                            store.getId(),
                            0.0
                    ) + price.getPrice()
            );
        }

        double lowestCost =
                Collections.min(storeTotals.values());

        List<StoreComparisonResponse> responses =
                new ArrayList<>();

        for (Long storeId : storeTotals.keySet()) {

            Store store =
                    stores.get(storeId);

            double totalCost =
                    storeTotals.get(storeId);

            List<String> pros =
                    new ArrayList<>();

            List<String> cons =
                    new ArrayList<>();

            if (totalCost == lowestCost) {
                pros.add(
                        "Lowest basket cost"
                );
            } else {
                cons.add(
                        "Higher basket cost than competitors"
                );
            }

            if (store.getRating() >= 4.5) {
                pros.add(
                        "Highly rated store"
                );
            }

            if (store.getDeliveryFee() <= 40) {
                pros.add(
                        "Low delivery fee"
                );
            } else {
                cons.add(
                        "Higher delivery fee"
                );
            }

            responses.add(
                    StoreComparisonResponse.builder()
                            .storeName(
                                    store.getName()
                            )
                            .rating(
                                    store.getRating()
                            )
                            .deliveryFee(
                                    store.getDeliveryFee()
                            )
                            .totalCost(
                                    totalCost
                            )
                            .pros(pros)
                            .cons(cons)
                            .build()
            );
        }

        responses.sort(
                Comparator.comparing(
                        StoreComparisonResponse::getTotalCost
                )
        );
        for (int i = 0; i < responses.size(); i++) {

            responses.get(i)
                    .setRank(i + 1);    
        }

        return responses;
    }
}