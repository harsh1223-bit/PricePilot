package com.groceryai.service;

import com.groceryai.dto.CreateGroceryListRequest;
import com.groceryai.entity.GroceryList;
import com.groceryai.repository.GroceryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;

    public GroceryList create(CreateGroceryListRequest request) {

        GroceryList groceryList = GroceryList.builder()
                .title(request.getTitle())
                .build();

        return groceryListRepository.save(groceryList);
    }

    public List<GroceryList> getAll() {
        return groceryListRepository.findAll();
    }

    public GroceryList getById(Long id) {
        return groceryListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grocery list not found"));
    }
}