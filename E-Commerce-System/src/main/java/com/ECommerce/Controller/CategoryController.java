package com.ECommerce.Controller;

import com.ECommerce.Entities.Category;
import com.ECommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);

    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<Category>getCategoryById(@PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatus.OK);
    }

    @GetMapping("/getCategoryByName")
    public ResponseEntity<Category>getCategoryByName(@RequestParam String categoryName){
        return new ResponseEntity<>(categoryService.getCategoryByName(categoryName),HttpStatus.OK);
    }

    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<Category>updateCategory(@PathVariable Long categoryId,@RequestBody Category updatedCategory){
        return new ResponseEntity<>(categoryService.updateCategory(categoryId,updatedCategory),HttpStatus.OK);
    }
}
