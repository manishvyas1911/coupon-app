package com.dnc.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.dnc.coupon.entity.Category;
import com.dnc.coupon.entity.Coupon;

@Component("couponRepo")
public interface CouponRepository extends JpaRepository<Coupon, Integer>{

	List<Coupon> findByCategory(Category category);

}
