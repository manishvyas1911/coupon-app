package com.dnc.coupon.service;

import java.util.List;

import com.dnc.coupon.entity.Category;
import com.dnc.coupon.entity.Coupon;

public interface CouponService {
	Coupon addCoupon(Coupon coupon);
	
	Coupon updateCoupon(Coupon coupon);
	
	List<Coupon> getAllCoupons();
	
	void deleteCoupon(int customerId);

	List<Coupon> getCouponByCategory(Category category);

	Coupon getCouponById(int couponId, boolean cacheEnable);
}
