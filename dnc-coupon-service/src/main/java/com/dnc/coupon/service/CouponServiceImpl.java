package com.dnc.coupon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dnc.coupon.entity.Category;
import com.dnc.coupon.entity.Coupon;
import com.dnc.coupon.exception.ResourceNotFoundException;
import com.dnc.coupon.repository.CouponCacheRepository;
import com.dnc.coupon.repository.CouponRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	@Qualifier("couponRepo")
	CouponRepository couponRepository;
	
	@Autowired
	@Qualifier("couponCache")
	CouponCacheRepository cacheRepository;

	@Override
	public Coupon addCoupon(Coupon coupon) {
		Coupon savedCoupon = couponRepository.save(coupon);
		log.info("v1/add {}",coupon);
		cacheRepository.save(savedCoupon);
		return savedCoupon;
	}

	@Override
	public Coupon updateCoupon(Coupon coupon) {
		Optional<Coupon> couponDb = this.couponRepository.findById(coupon.getId());
		if(couponDb.isPresent()) {
			Coupon couponUpdate = couponDb.get();
			couponUpdate.setId(coupon.getId());
			couponUpdate.setName(coupon.getName());
			couponUpdate.setDescription(coupon.getDescription());
			couponUpdate.setCategory(coupon.getCategory());
			couponUpdate.setValue(coupon.getValue());
			// update cache
			cacheRepository.save(couponUpdate);
			
			// Update db
			couponRepository.save(couponUpdate);
			
			return couponUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id: "+ coupon.getId());
		}
	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}

	@Override
	public Coupon getCouponById(int couponId, boolean cacheEnable) {
		if(cacheEnable) {
			Coupon coupon = cacheRepository.findCouponById(couponId); 
			if(coupon != null) {
				log.info("GetCouponByIdDataFecher Cached coupon: {}", coupon);
				return coupon;
			}else {
				Optional<Coupon> dbCoupon = couponRepository.findById(couponId);
				if(dbCoupon.isPresent()) {
					log.info("GetCouponByIdDataFecher DB coupon: {}", dbCoupon);
					return dbCoupon.get();
				}else {
					return null;
				}
			}
			
		}else {
			Optional<Coupon> dbCoupon = couponRepository.findById(couponId);
			if(dbCoupon.isPresent()) {
				log.info("GetCouponByIdDataFecher DB coupon: {}", dbCoupon);
				return dbCoupon.get();
			}else {
				return null;
			}
		}
		
	}
	
	@Override
	public List<Coupon> getCouponByCategory(Category category) {
		List<Coupon> couponsDb = this.couponRepository.findByCategory(category);
		return couponsDb;
	}
	
	@Override
	public void deleteCoupon(int couponId) {
		Optional<Coupon> couponDb = this.couponRepository.findById(couponId);
		if(couponDb.isPresent()) {
			couponRepository.delete(couponDb.get());
			cacheRepository.deleteCoupon(couponDb.get().getId());
		}else {
			throw new ResourceNotFoundException("Record not found with id: "+ couponId);
		}
		
	}
}
