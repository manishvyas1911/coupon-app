package com.mani.cf.service.datafetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class GetCouponByIdDataFecher implements DataFetcher<Coupon> {

	public static final String HASH_KEY = "Coupon";

	
	@Autowired
	CouponRepository repository;
	
	//@Autowired
	//private RedisTemplate template;
	
	@Override	
	public Coupon get(DataFetchingEnvironment environment) {
		int id=environment.getArgument("id");
		return  repository.findCouponById(id); 
		 
	}

}
