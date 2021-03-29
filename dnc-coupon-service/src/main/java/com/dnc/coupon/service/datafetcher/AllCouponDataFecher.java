package com.dnc.coupon.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dnc.coupon.entity.Coupon;
import com.dnc.coupon.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class AllCouponDataFecher implements DataFetcher<List<Coupon>>{

	@Autowired
	@Qualifier("couponRepo")
	CouponRepository repository;
	
	
	@Override
	public List<Coupon> get(DataFetchingEnvironment environment) {
		return repository.findAll();
	}

}
