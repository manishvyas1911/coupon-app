package com.mani.cf.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class DeleteCouponDataFecher implements DataFetcher<Boolean>  {
	
	public static final String HASH_KEY = "Coupon";

	
	//@Autowired
	//private RedisTemplate template;

	@Autowired
	CouponRepository repository;
	
	//@CacheEvict(key="#id",value="Coupon")
	@Override
	public Boolean get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		Coupon op =  repository.findCouponById(environment.getArgument("id"));
		if(op != null) {
			// template.opsForHash().delete(HASH_KEY, op.get().getId());
			repository.deleteCoupon(op.getId());
			return true;
		}else {
			return false;
		}
		
	}

}
