package com.mani.cf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mani.cf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String username);

}
