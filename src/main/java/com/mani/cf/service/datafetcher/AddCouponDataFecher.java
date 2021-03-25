package com.mani.cf.service.datafetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.mani.cf.constant.Category;
import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AddCouponDataFecher implements DataFetcher<Coupon>  {
	
	public static final String HASH_KEY = "Coupon";

	private static Logger logger = LoggerFactory.getLogger(AddCouponDataFecher.class);
	
	@Autowired
	CouponRepository repository;
	
	@Override
	public Coupon get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		
		String name = environment.getArgument("name");
		String description = environment.getArgument("description");
		int value = environment.getArgument("value");
		Category category = Category.valueOf(environment.getArgument("category"));
		
		Coupon coupon = new Coupon(name,description, category, value);
		
		logger.info("{}",coupon);
		
		return repository.save(coupon);
	}

}
