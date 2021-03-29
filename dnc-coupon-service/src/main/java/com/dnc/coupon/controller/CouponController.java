package com.dnc.coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnc.coupon.entity.Category;
import com.dnc.coupon.entity.Coupon;
import com.dnc.coupon.service.CouponService;
import com.dnc.coupon.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/coupon")
@RestController
public class CouponController {
	
	@Autowired
	private GraphQLService graphQLService;
	
	@Autowired
	private CouponService couponService;

	@PostMapping("/graphql")
	public ResponseEntity<Object> couponGraphQL(@RequestBody String query){
		ExecutionResult execResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execResult, HttpStatus.OK);
	}
	
	// Traditional endpoints
	
	@GetMapping
	public ResponseEntity<List<Coupon>> getAllCoupons(){
		return ResponseEntity.ok().body(couponService.getAllCoupons());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Coupon> getCouponById(@PathVariable int id){
		return ResponseEntity.ok().body(couponService.getCouponById(id, true));
	}
	
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<List<Coupon>> getCouponById(@PathVariable String categoryName){
		return ResponseEntity.ok().body(couponService.getCouponByCategory(Category.valueOf(categoryName)));
	}
	
	
	@PostMapping
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){
		return ResponseEntity.ok().body(couponService.addCoupon(coupon));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Coupon> updateCoupon(@PathVariable int id, @RequestBody Coupon coupon){
		coupon.setId(id);
		return ResponseEntity.ok().body(couponService.updateCoupon(coupon));
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteCoupon(@PathVariable int id){
		couponService.deleteCoupon(id);
		return HttpStatus.OK;
	}
	
	
	
	
	/*
	
	@PostMapping("/all")
	public ResponseEntity<Object> getAllCoupons(@RequestBody String query){
		ExecutionResult execResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execResult, HttpStatus.OK);
	}
	
	
	@PostMapping("/{id}")
	@Cacheable(key="#id",value="Coupon")
	public ResponseEntity<Object> getCouponById(@RequestBody String query){
		
		ExecutionResult execResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execResult, HttpStatus.OK);
	}
	
	@PostMapping("/delete/{id}")
	@CacheEvict(key="#id",value="Coupon")
	public ResponseEntity<Object> cancelBooking(@RequestBody String query){
		ExecutionResult execResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execResult, HttpStatus.OK);
	}
	
	*/

}
