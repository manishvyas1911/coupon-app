package com.mani.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.cf.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/coupon")
@RestController
public class CouponController {
	
	@Autowired
	private GraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllCoupons(@RequestBody String query) {
		ExecutionResult execResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execResult, HttpStatus.OK);
	}

}
