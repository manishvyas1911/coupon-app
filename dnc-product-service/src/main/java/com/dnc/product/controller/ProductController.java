package com.dnc.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnc.product.entity.Product;
import com.dnc.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    
    
    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/category/{id}")
    public Product getProductsWithCoupon(@PathVariable int id) {
        return productService.getProductWithCoupon(id);
    }
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
    	Optional<Product> opProduct = productService.getProductById(id);
    	
        if(opProduct.isPresent()) {
            return opProduct.get();
            }
        else {
        	return  null;
        	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(product, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
