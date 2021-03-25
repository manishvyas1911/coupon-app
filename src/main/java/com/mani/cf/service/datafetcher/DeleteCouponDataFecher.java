package com.mani.cf.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DeleteCouponDataFecher implements DataFetcher<Boolean>  {
	
	public static final String HASH_KEY = "Coupon";
	@Autowired
	private RedisTemplate template;

	@Autowired
	CouponRepository repository;
	
	@Override
	@CacheEvict(key="#id",value="Coupon")
	public Boolean get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		Optional<Coupon> op =  repository.findById(environment.getArgument("id"));
		if(op.isPresent()) {
			template.opsForHash().delete(HASH_KEY, op.get().getId());
			repository.delete(op.get());
			return true;
		}else {
			return false;
		}
		
	}

}
