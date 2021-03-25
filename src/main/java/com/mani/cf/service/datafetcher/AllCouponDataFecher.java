package com.mani.cf.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllCouponDataFecher implements DataFetcher<List<Coupon>>{

	@Autowired
	CouponRepository repository;
	
	
	@Override
	public List<Coupon> get(DataFetchingEnvironment environment) {
		return repository.findAll();
	}

}
