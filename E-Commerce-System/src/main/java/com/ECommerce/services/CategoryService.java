package com.ECommerce.services;

import com.ECommerce.Entities.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

    Category getCategoryByName(String name);

    Category updateCategory(Long id, Category updatedcategory);

    void deleteCategory(Long oldCategoryId, Long newCategoryId);

    Category activateCategory(Long id);
}
