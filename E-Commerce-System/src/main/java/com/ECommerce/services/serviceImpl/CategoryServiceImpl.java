package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.Category;
import com.ECommerce.Entities.Product;
import com.ECommerce.repository.CategoryRepository;
import com.ECommerce.repository.ProductRepository;
import com.ECommerce.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;



    //   create category
    @Override
    public Category createCategory(Category category) {
        if (categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName()).isPresent()) {
            throw new RuntimeException("Category already exist");
        }
        category.setStatus(true);
        return categoryRepository.save(category);
    }

//    get All categories

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

//    get category by id

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found by given id : " + id));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByCategoryNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

    @Override
    public Category updateCategory(Long id, Category updatedcategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found by given id : " + id));
        category.setCategoryName(updatedcategory.getCategoryName());
        category.setDescription(updatedcategory.getDescription());

        return categoryRepository.save(category);
    }

    //   delete category with warning
    @Transactional
    public void deleteCategory(Long oldCategoryId, Long newCategoryId) {
        Category oldCategory = categoryRepository.findById(oldCategoryId)
                .orElseThrow(() -> new RuntimeException("Old category not found"));
        Category newCategory = categoryRepository.findById(newCategoryId)
                .orElseThrow(() -> new RuntimeException("New category not found"));
        List<Product> products = productRepository.findByCategory(oldCategory);
        for (Product product : products) {
            product.setCategory(newCategory);
        }
        productRepository.saveAll(products);
        oldCategory.setStatus(false);
        categoryRepository.save(oldCategory);

    }

    @Override
    public Category activateCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new
                RuntimeException("Category with id not found"));
        category.setStatus(true);
        return categoryRepository.save(category);
    }
}




