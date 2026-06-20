package com.groceryai.repository;

import com.groceryai.entity.GroceryList;
import com.groceryai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryListRepository
        extends JpaRepository<GroceryList, Long> {

    List<GroceryList> findByUser(User user);
}