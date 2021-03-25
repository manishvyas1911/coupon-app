package com.mani.cf.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import com.mani.cf.constant.Category;
import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UpdateCouponByIdDataFecher implements DataFetcher<Coupon>  {

	@Autowired
	CouponRepository repository;
	
	@Override	
	public Coupon get(DataFetchingEnvironment environment) {
		Optional<Coupon> op =  repository.findById(environment.getArgument("id"));
		if(op.isPresent()) {
			System.out.println(op.get());
			Coupon dbCoupon = op.get();
			dbCoupon.setName(environment.getArgument("name"));
			dbCoupon.setDescription(environment.getArgument("description"));
			dbCoupon.setValue(environment.getArgument("value"));
			dbCoupon.setCategory(Category.valueOf(environment.getArgument("category")));
			return repository.save(dbCoupon);
		}else {
			return null;
		}
	}

}
