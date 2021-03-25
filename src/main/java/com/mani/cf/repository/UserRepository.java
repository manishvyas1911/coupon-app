package com.mani.cf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.cf.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String username);

}
