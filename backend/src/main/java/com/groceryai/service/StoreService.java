package com.groceryai.service;

import com.groceryai.dto.CreateStoreRequest;
import com.groceryai.dto.StoreResponse;
import com.groceryai.entity.Store;
import com.groceryai.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResponse create(
            CreateStoreRequest request
            
    ) {

        Store store = Store.builder()
                .freeDeliveryThreshold(
                    request.getFreeDeliveryThreshold()
                )
                .name(request.getName())
                .deliveryFee(request.getDeliveryFee())
                .rating(request.getRating())
                .address(request.getAddress())
                .build();
                

        Store savedStore =
                storeRepository.save(store);

        return mapToResponse(savedStore);
    }

    public List<StoreResponse> getAll() {

        return storeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private StoreResponse mapToResponse(
            Store store
    ) {

        return StoreResponse.builder()
                .freeDeliveryThreshold(
                        store.getFreeDeliveryThreshold()
                )
                .id(store.getId())
                .name(store.getName())
                .deliveryFee(store.getDeliveryFee())
                .rating(store.getRating())
                .address(store.getAddress())
                .build();
    }
}