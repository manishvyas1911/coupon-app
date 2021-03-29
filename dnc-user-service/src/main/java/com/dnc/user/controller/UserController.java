package com.dnc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnc.user.model.LoginRequest;
import com.dnc.user.model.User;
import com.dnc.user.security.JwtUtil;
import com.dnc.user.service.UserService;

@RequestMapping("/auth")
@RestController
public class UserController {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public String welcomeMessage() {
		return "Auth Test";
	}

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User savedUSer = userService.addUser(user);
		return new ResponseEntity<User>(savedUSer, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updUser = userService.updateUser(user.getId(), user);
		return new ResponseEntity<User>(updUser, HttpStatus.CREATED);
	}

	
	@PostMapping("/login")
	public String generateToken(@RequestBody LoginRequest loginReq) throws Exception {
		try{authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginReq.getUserName(), loginReq.getPassword())
				);
		}
		catch(Exception ex) {
			throw new Exception("Invalid Credentials");
		}
		return  jwtUtil.generateToken(loginReq.getUserName());
	}
}
