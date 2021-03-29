package com.dnc.coupon.service.datafetcher;

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
public class GetCouponByIdDataFecher implements DataFetcher<Coupon> {

	public static final String HASH_KEY = "Coupon";

	
	@Autowired
	@Qualifier("couponRepo")
	CouponRepository repository;
	
	@Autowired
	@Qualifier("couponCache")
	CouponCacheRepository cacheRepository;

	@Override	
	public Coupon get(DataFetchingEnvironment environment) {
		int id=environment.getArgument("id");
		
		Coupon coupon = cacheRepository.findCouponById(id); 
		if(coupon != null) {
			log.info("GetCouponByIdDataFecher Cached coupon: {}", coupon);
			return coupon;
		}else {
			Optional<Coupon> dbCoupon = repository.findById(id);
			if(dbCoupon.isPresent()) {
				log.info("GetCouponByIdDataFecher DB coupon: {}", coupon);
				return dbCoupon.get();
			}else {
				return null;
			}
		}
		
	}
	


}
