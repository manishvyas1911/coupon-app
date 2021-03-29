package com.dnc.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DncCouponServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DncCouponServiceApplication.class, args);
	}

}
