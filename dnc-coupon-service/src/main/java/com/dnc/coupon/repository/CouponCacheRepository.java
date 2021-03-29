package com.dnc.coupon.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dnc.coupon.entity.Category;
import com.dnc.coupon.entity.Coupon;
import com.dnc.coupon.service.datafetcher.GetCouponByIdDataFecher;

import lombok.extern.slf4j.Slf4j;

@Component("couponCache")
@Slf4j
public class CouponCacheRepository{
	
	
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
		
		log.info("Called Cached Coupon");
	        return (Coupon)template.opsForHash().get(HASH_KEY,id);
	    }
	
	public List<Coupon> findCouponByCategory(Category category){
		
		log.info("Called Cached Coupon");
	        return (List<Coupon>)template.opsForHash().get(HASH_KEY,category);
	    }

	public String deleteCoupon(int id){
	         template.opsForHash().delete(HASH_KEY, id);
	        return "Coupon with id:"+id+ " deleted succesfully!";
	    }

}
