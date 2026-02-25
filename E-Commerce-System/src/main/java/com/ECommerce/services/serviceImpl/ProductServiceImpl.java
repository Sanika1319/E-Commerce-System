package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.Category;
import com.ECommerce.Entities.Product;
import com.ECommerce.repository.CategoryRepository;
import com.ECommerce.repository.ProductRepository;
import com.ECommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(Long categoryId,Product product) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found By give productId: " + productId));
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        if (updatedProduct.getCategory() != null) {
            Long categoryId = updatedProduct.getCategory().getCategoryId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }

    @Override
    public List<Product> searchProductByName(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(min,max);
    }

    @Override
    public List<Product> getLowStockProducts() {
        return productRepository.findByQuantityLessThan(50);
    }

    @Override
    public List<Product> getOutOfStockProducts() {
        return productRepository.findByQuantity(0);
    }
}
