package com.groceryai.service;

import com.groceryai.dto.*;
import com.groceryai.entity.*;
import com.groceryai.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final ProductRepository productRepository;

    public ShoppingList createList(
            CreateShoppingListRequest request
    ) {

        ShoppingList list =
                    ShoppingList.builder()
                            .name(request.getName())
                            .createdAt(LocalDateTime.now())
                            .build();

        return shoppingListRepository.save(list);
    }

    public ShoppingListItem addItem(
            Long listId,
            AddShoppingListItemRequest request
    ) {

        ShoppingList list =
                shoppingListRepository.findById(listId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Shopping list not found"
                                ));

        Product product =
                productRepository.findById(
                                request.getProductId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"
                                ));

        ShoppingListItem item =
                ShoppingListItem.builder()
                        .shoppingList(list)
                        .product(product)
                        .quantity(request.getQuantity())
                        .build();

        return shoppingListItemRepository.save(item);
    }

    public List<ShoppingListResponse> getLists() {

        return shoppingListRepository.findAll()
                .stream()
                .map(list ->
                        ShoppingListResponse.builder()
                                .id(list.getId())
                                .name(list.getName())
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<ShoppingListItemResponse>
    getItems(Long listId) {

        return shoppingListItemRepository
                .findByShoppingListId(listId)
                .stream()
                .map(item ->
                        ShoppingListItemResponse
                                .builder()
                                .productId(
                                        item.getProduct()
                                                .getId()
                                )
                                .productName(
                                        item.getProduct()
                                                .getName()
                                )
                                .quantity(
                                        item.getQuantity()
                                )
                                .build()
                )
                .collect(Collectors.toList());
    }
}