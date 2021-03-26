package com.mani.cf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mani.cf.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
