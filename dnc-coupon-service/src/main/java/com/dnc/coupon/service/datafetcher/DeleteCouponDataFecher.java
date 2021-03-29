package com.dnc.coupon.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dnc.coupon.entity.Coupon;
import com.dnc.coupon.repository.CouponCacheRepository;
import com.dnc.coupon.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class DeleteCouponDataFecher implements DataFetcher<Boolean>  {
	
	public static final String HASH_KEY = "Coupon";

	
	@Autowired
	@Qualifier("couponRepo")
	CouponRepository repository;
	
	@Autowired
	@Qualifier("couponCache")
	CouponCacheRepository cacheRepository;
	
	@Override
	public Boolean get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		Optional<Coupon> coupon =  repository.findById(environment.getArgument("id"));
		if(coupon.isPresent()) {
			repository.delete(coupon.get());
			Coupon op =  cacheRepository.findCouponById(environment.getArgument("id"));
			if(op != null) {
				cacheRepository.deleteCoupon(op.getId());
			}
			//check if deleted
			return true;
		}else {
			return false;
		}
		
	
		
	}

}
