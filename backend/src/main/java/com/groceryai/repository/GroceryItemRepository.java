package com.groceryai.repository;

import com.groceryai.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryItemRepository
        extends JpaRepository<GroceryItem, Long> {

    List<GroceryItem> findByGroceryListId(Long groceryListId);
}