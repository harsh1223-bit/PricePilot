package com.groceryai.repository;

import com.groceryai.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository
        extends JpaRepository<ShoppingList, Long> {

}