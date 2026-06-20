package com.groceryai.repository;

import com.groceryai.entity.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListItemRepository
        extends JpaRepository<ShoppingListItem, Long> {

    List<ShoppingListItem>
    findByShoppingListId(Long shoppingListId);
}