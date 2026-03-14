package com.ECommerce.services;

import com.ECommerce.Entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Long categoryId,Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long productId, Product updatedProduct);
    List<Product> getProductsByCategory(Long categoryId);
    List<Product> searchProductByName(String keyword);

    List<Product> getProductsByPriceRange(double min, double max);
    List<Product> getLowStockProducts();

    List<Product> getOutOfStockProducts();

    void deleteProduct(Long id);
}
