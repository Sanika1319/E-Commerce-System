package com.ECommerce.Controller;

import com.ECommerce.Entities.Category;
import com.ECommerce.Entities.Product;
import com.ECommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createProduct/{categoryId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long categoryId,@RequestBody Product product){
      return new ResponseEntity<>(productService.createProduct(categoryId,product), HttpStatus.CREATED);
    }


    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct){
      return new ResponseEntity<>(productService.updateProduct(productId,updatedProduct),HttpStatus.OK);
    }
    @GetMapping("/getProductsByCategory/{categoryId}")
    public ResponseEntity<List<Product>>getProductsByCategory(@PathVariable Long categoryId){
        return new ResponseEntity<>(productService.getProductsByCategory(categoryId),HttpStatus.OK);
    }
    @GetMapping("/searchProductByName")
    public ResponseEntity<List<Product>> searchProductByName(@RequestParam String keyword){
        return new ResponseEntity<>(productService.searchProductByName(keyword),HttpStatus.OK);
    }

    @GetMapping("/getProductsByPriceRange")
    public ResponseEntity<List<Product>>getProductsByPriceRange(@RequestParam double min,@RequestParam double max){
        return new ResponseEntity<>(productService.getProductsByPriceRange(min, max),HttpStatus.OK);
    }

    @GetMapping("/getLowStockProducts")
    public ResponseEntity<List<Product>>getLowStockProducts(){
        return new ResponseEntity<>(productService.getLowStockProducts(),HttpStatus.OK);
    }

    @GetMapping("/getOutOfStockProducts")
    public ResponseEntity<List<Product>>getOutOfStockProducts(){
        return new ResponseEntity<>(productService.getOutOfStockProducts(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        System.out.println("INSIDE DELETE...............................................");
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully ",HttpStatus.OK);
    }

}
