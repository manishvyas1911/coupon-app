package com.mani.cf;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.mani.cf.model.User;
import com.mani.cf.repository.UserRepository;

@SpringBootApplication
@EnableCaching
public class CouponfinderApplication {	
	
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void initUser() {
		/*
		 * List<User> users = Stream.of( 
		 * new User(1L,"admin","admin","Administrator"),
		 * new User(2L,"mani","mani","Manish") )
		 * .collect(Collectors.toList());
		 */
		
		userRepository.saveAll(Collections.singletonList(new User(1L,"admin","admin","Administrator")));
	}

	public static void main(String[] args) {
		SpringApplication.run(CouponfinderApplication.class, args);
	}

}
