package com.dnc.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.dnc.product.entity.Product;

public interface ProductService {

	ResponseEntity<Object> createProduct(Product product);

	List<Product> getAllProducts();

	Optional<Product> getProductById(int id);

	ResponseEntity<Object> updateProduct(Product product, int id);

	ResponseEntity<Object> deleteProduct(int id);

	Product getProductWithCoupon(int id);

}
