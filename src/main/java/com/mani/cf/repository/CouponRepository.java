package com.mani.cf.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mani.cf.model.Coupon;
import com.mani.cf.service.datafetcher.GetCouponByIdDataFecher;

@Repository
public class CouponRepository{
	
	Logger logger = LoggerFactory.getLogger(GetCouponByIdDataFecher.class);
	
	public static final String HASH_KEY = "Coupon";
	@Autowired
	private RedisTemplate template;

	public Coupon save(Coupon coupon){
	        template.opsForHash().put(HASH_KEY,coupon.getId(),coupon);
	        return coupon;
	    }

	public List<Coupon> findAll(){
	        return template.opsForHash().values(HASH_KEY);
	    }

	public Coupon findCouponById(int id){
		
		logger.info("Called Cached Coupon");
	        return (Coupon)template.opsForHash().get(HASH_KEY,id);
	    }

	public String deleteCoupon(int id){
	         template.opsForHash().delete(HASH_KEY, id);
	        return "Coupon with id:"+id+ " deleted succesfully!";
	    }

}
