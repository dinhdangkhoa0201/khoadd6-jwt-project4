package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.CreateUserRequest;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@GetMapping("/id/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UserEntity> findByUserName(@PathVariable String username) {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			log.error("UserName is not exist");
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserRequest createUserRequest) {
		UserEntity user = new UserEntity();
		try {
			user.setUsername(createUserRequest.getUsername());
			user.setPassword(createUserRequest.getPassword());
			CartEntity cart = new CartEntity();
			cartRepository.save(cart);
			user.setCart(cart);
			user = userRepository.save(user);
			log.debug(HttpStatus.CREATED + " - Create User Successfully");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(user);
	}
	
}
