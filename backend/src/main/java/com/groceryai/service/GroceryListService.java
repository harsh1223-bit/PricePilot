package com.groceryai.service;

import com.groceryai.dto.CreateGroceryListRequest;
import com.groceryai.entity.GroceryList;
import com.groceryai.entity.User;
import com.groceryai.repository.GroceryListRepository;
import com.groceryai.repository.UserRepository;
import com.groceryai.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    public GroceryList create(CreateGroceryListRequest request) {

        String email = securityUtil.getCurrentUserEmail();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        GroceryList groceryList = GroceryList.builder()
                .title(request.getTitle())
                .user(currentUser)
                .build();

        return groceryListRepository.save(groceryList);
    }

    public List<GroceryList> getAll() {

        String email = securityUtil.getCurrentUserEmail();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return groceryListRepository.findByUser(currentUser);
    }

    public GroceryList getById(Long id) {

        GroceryList groceryList = groceryListRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Grocery list not found"));

        String email = securityUtil.getCurrentUserEmail();

        if (groceryList.getUser() == null ||
                !groceryList.getUser().getEmail().equals(email)) {

            throw new RuntimeException("Access denied");
        }

        return groceryList;
    }
}