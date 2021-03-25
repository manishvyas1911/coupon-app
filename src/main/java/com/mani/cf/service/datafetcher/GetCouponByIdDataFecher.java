package com.mani.cf.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GetCouponByIdDataFecher implements DataFetcher<Coupon> {

	@Autowired
	CouponRepository repository;
	
	@Override	
	public Coupon get(DataFetchingEnvironment environment) {
		return repository.findById(environment.getArgument("id")).get();
	}

}
