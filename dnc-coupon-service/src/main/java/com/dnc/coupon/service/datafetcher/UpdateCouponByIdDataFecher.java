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

@Service
public class UpdateCouponByIdDataFecher implements DataFetcher<Coupon>  {

	public static final String HASH_KEY = "Coupon";
	

	@Autowired
	@Qualifier("couponRepo")
	CouponRepository repository;
	
	@Autowired
	@Qualifier("couponCache")
	CouponCacheRepository cacheRepository;
	
	@Override	
	public Coupon get(DataFetchingEnvironment environment) {
		Optional<Coupon> opCoupon =  repository.findById(environment.getArgument("id"));
		if(opCoupon.isPresent()) {
			Coupon dbCoupon = opCoupon.get();
			
			dbCoupon.setName(environment.getArgument("name"));
			dbCoupon.setDescription(environment.getArgument("description"));
			dbCoupon.setValue(environment.getArgument("value"));
			dbCoupon.setCategory(Category.valueOf(environment.getArgument("category")));	
			cacheRepository.save(dbCoupon);

			return repository.save(dbCoupon);
		}else {
			return null;
		}
	}

}
