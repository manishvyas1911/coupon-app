package com.mani.cf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.cf.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
