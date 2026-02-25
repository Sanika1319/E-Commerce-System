package com.ECommerce.repository;

import com.ECommerce.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Long countByCategory_categoryId(Long categoryId);

    List<Product> findByCategoryCategoryId(Long categoryId);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByPriceBetween(double min, double max);

    List<Product> findByQuantityLessThan(int quantity);

    List<Product> findByQuantity(int quantity);
}
