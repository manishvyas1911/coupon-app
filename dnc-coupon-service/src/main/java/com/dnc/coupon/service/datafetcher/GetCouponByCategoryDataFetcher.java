package com.dnc.coupon.service.datafetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dnc.coupon.entity.Category;
import com.dnc.coupon.entity.Coupon;
import com.dnc.coupon.repository.CouponCacheRepository;
import com.dnc.coupon.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetCouponByCategoryDataFetcher implements DataFetcher<List<Coupon>>{
public static final String HASH_KEY = "Coupon";

	
	@Autowired
	@Qualifier("couponRepo")
	CouponRepository repository;
	
	@Autowired
	@Qualifier("couponCache")
	CouponCacheRepository cacheRepository;

	@Override	
	public List<Coupon> get(DataFetchingEnvironment environment) {
		Category category = Category.valueOf(environment.getArgument("category"));
		/*
		log.info("GetCouponByIdDataFecher Category:", category.toString());
		List<Coupon> coupons = cacheRepository.findCouponByCategory(category); 
			log.info("GetCouponByIdDataFecher Cached coupon: {}", coupons);
			return coupons;
		 */
	
		return repository.findByCategory(category);
	
	}

}
