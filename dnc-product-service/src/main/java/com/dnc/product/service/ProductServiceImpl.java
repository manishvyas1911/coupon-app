package com.dnc.product.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dnc.product.entity.Coupon;
import com.dnc.product.entity.Product;
import com.dnc.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	 /** Create a new Product */
	@Override
    public ResponseEntity<Object> createProduct(Product model) {
        if (productRepository.findByName(model.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Product Name is already Present, Failed to Create new Product");
        } else {
        	
            Product savedProduct = productRepository.save(model);
            if (productRepository.findById(savedProduct.getId()).isPresent())
                return ResponseEntity.ok("Product Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating Product as Specified");
        }
    }
    

    /** Update an Existing Product */
    @Transactional
    @Override
    public ResponseEntity<Object> updateProduct(Product product, int id) {
        if(productRepository.findById(id).isPresent()) {
            Product newProduct = productRepository.findById(id).get();
            newProduct.setName(product.getName());
            newProduct.setDescription(product.getDescription());
            newProduct.setPrice(product.getPrice());
            newProduct.setCategory(product.getCategory());
            // newProduct.setCoupons(product.getCoupons());
            Product savedProduct = productRepository.save(newProduct);
            if(productRepository.findById(savedProduct.getId()).isPresent())
                return  ResponseEntity.accepted().body("Product updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the product specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the product specified");
    }
    /** Delete an Product*/
    @Override
    public ResponseEntity<Object> deleteProduct(int id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            if (productRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified Product");
            else return ResponseEntity.ok().body("Successfully deleted the specified product");
        } else return ResponseEntity.badRequest().body("Cannot find the product specified");
    }

    @Override
	public Optional<Product> getProductById(int id) {
		
		return productRepository.findById(id);
	}

    @Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}


	@Override
	public Product getProductWithCoupon(int id ) {
		Optional<Product> opProduct = productRepository.findById(id);
		if(opProduct.isPresent()) {
			Product dbProduct = opProduct.get();
			ResponseEntity<Coupon[]> response =  restTemplate.getForEntity("http://COUPON-SERVICE/coupon/category/"+ dbProduct.getCategory()
			                                               , Coupon[].class);
			Coupon[] coupons = response.getBody();
			dbProduct.setCoupons(Arrays.asList(coupons));
			return dbProduct;
		}else {
		return null;
		}
	}

}
