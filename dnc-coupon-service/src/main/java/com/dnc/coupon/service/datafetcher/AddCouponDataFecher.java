package com.dnc.coupon.service.datafetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dnc.coupon.entity.Category;
import com.dnc.coupon.entity.Coupon;
import com.dnc.coupon.repository.CouponCacheRepository;
import com.dnc.coupon.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class AddCouponDataFecher implements DataFetcher<Coupon>  {
	
	public static final String HASH_KEY = "Coupon";

	private static Logger logger = LoggerFactory.getLogger(AddCouponDataFecher.class);
	
	@Autowired
	@Qualifier("couponRepo")
	CouponRepository repository;
	
	@Autowired
	@Qualifier("couponCache")
	CouponCacheRepository cacheRepository;
	
	@Override
	public Coupon get(DataFetchingEnvironment environment) {

		String name = environment.getArgument("name");
		String description = environment.getArgument("description");
		int value = environment.getArgument("value");
		Category category = Category.valueOf(environment.getArgument("category"));
		
		Coupon coupon = new Coupon();
		coupon.setName(name);
		coupon.setDescription(description);
		coupon.setCategory(category);
		coupon.setValue(value);
		
		Coupon savedCoupon = repository.save(coupon);
		//int id = environment.getArgument("id");
		logger.info("{}",coupon);
		cacheRepository.save(savedCoupon);
		
		return savedCoupon;
	}

}
