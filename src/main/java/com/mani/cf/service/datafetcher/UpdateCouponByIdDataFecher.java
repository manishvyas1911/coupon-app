package com.mani.cf.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mani.cf.constant.Category;
import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class UpdateCouponByIdDataFecher implements DataFetcher<Coupon>  {

	public static final String HASH_KEY = "Coupon";
	
	//@Autowired
	//private RedisTemplate template;
	
	@Autowired
	CouponRepository repository;
	
	@Override	
	public Coupon get(DataFetchingEnvironment environment) {
		Coupon coupon =  repository.findCouponById(environment.getArgument("id"));
		if(coupon != null) {
		
		
			coupon.setName(environment.getArgument("name"));
			coupon.setDescription(environment.getArgument("description"));
			coupon.setValue(environment.getArgument("value"));
			coupon.setCategory(Category.valueOf(environment.getArgument("category")));
			Coupon updCoupon = repository.save(coupon);
			//template.opsForHash().put(HASH_KEY, updCoupon.getId(), updCoupon);
			return updCoupon;
		}else {
			return null;
		}
	}

}
