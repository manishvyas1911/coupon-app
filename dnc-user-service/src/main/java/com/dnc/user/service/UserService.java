package com.dnc.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnc.user.model.User;
import com.dnc.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(Long id, User user) {
		Optional<User> OpUser = userRepository.findById(id);
		if(OpUser.isPresent()) {
			User dbUser = OpUser.get();
			dbUser.setName(user.getName());
			dbUser.setUserName(user.getUserName());
			dbUser.setPassword(user.getPassword());
			
			return userRepository.save(dbUser);
		}else {
			return user;
		}
		
	}
}
