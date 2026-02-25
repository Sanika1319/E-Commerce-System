package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.Category;
import com.ECommerce.repository.CategoryRepository;
import com.ECommerce.repository.ProductRepository;
import com.ECommerce.services.CategoryService;
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
        if(categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName()).isPresent()){
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

//    get category by Id

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category not found by given id : "+id));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByCategoryNameIgnoreCase(name).orElseThrow(()->new RuntimeException("Category Not Found"));
    }

    @Override
    public Category updateCategory(Long id, Category updatedcategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found by given id : " + id));
        category.setCategoryName(updatedcategory.getCategoryName());
        category.setDescription(updatedcategory.getDescription());

        return categoryRepository.save(category);
    }

//   delete category with warning
    @Override
    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with given id: " + id));
        Long productCount = productRepository.countByCategory_categoryId(id);
        if(productCount > 0){
            return "Cannot deactivate category. Please assign products to another category first.";
        }
        category.setStatus(false);
        categoryRepository.save(category);

        return "Category deactivated successfully.";
    }
}
