package com.groceryai.repository;

import com.groceryai.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductPriceRepository
        extends JpaRepository<ProductPrice, Long> {

    List<ProductPrice> findByProductId(Long productId);

    Optional<ProductPrice>
    findFirstByProductIdOrderByPriceAsc(Long productId);

    List<ProductPrice> findByStoreName(String storeName);

    List<ProductPrice> findAll();
}