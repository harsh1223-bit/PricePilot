package com.groceryai.repository;

import com.groceryai.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository
        extends JpaRepository<Store, Long> {
}