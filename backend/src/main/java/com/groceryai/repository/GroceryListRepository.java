package com.groceryai.repository;

import com.groceryai.entity.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryListRepository
        extends JpaRepository<GroceryList, Long> {
}