package com.groceryai.service;

import com.groceryai.dto.AddItemRequest;
import com.groceryai.entity.GroceryItem;
import com.groceryai.entity.GroceryList;
import com.groceryai.entity.User;
import com.groceryai.repository.GroceryItemRepository;
import com.groceryai.repository.GroceryListRepository;
import com.groceryai.repository.UserRepository;
import com.groceryai.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;
    private final GroceryListRepository groceryListRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    public GroceryItem addItem(
            Long listId,
            AddItemRequest request
    ) {

        GroceryList list = groceryListRepository
                .findById(listId)
                .orElseThrow(() ->
                        new RuntimeException("Grocery list not found"));

        String email = securityUtil.getCurrentUserEmail();

        User currentUser = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (list.getUser() == null ||
                !list.getUser().getId().equals(currentUser.getId())) {

            throw new RuntimeException("Access denied");
        }

        GroceryItem item = GroceryItem.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .groceryList(list)
                .build();

        return groceryItemRepository.save(item);
    }
}