package com.groceryai.service;

import com.groceryai.dto.AddItemRequest;
import com.groceryai.entity.GroceryItem;
import com.groceryai.entity.GroceryList;
import com.groceryai.repository.GroceryItemRepository;
import com.groceryai.repository.GroceryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;
    private final GroceryListRepository groceryListRepository;

    public GroceryItem addItem(
            Long listId,
            AddItemRequest request
    ) {

        GroceryList list = groceryListRepository
                .findById(listId)
                .orElseThrow();

        GroceryItem item = GroceryItem.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .groceryList(list)
                .build();

        return groceryItemRepository.save(item);
    }
}