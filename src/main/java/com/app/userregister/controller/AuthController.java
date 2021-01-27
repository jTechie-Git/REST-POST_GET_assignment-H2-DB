package com.app.userregister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.userregister.dto.RegisterRequest;
import com.app.userregister.exception.UserNotFoundException;
import com.app.userregister.model.User;
import com.app.userregister.repository.UserRepository;
import com.app.userregister.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<RegisterRequest> register(@RequestBody RegisterRequest registerRequest) {
		authService.register(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/alluser")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	// Put Operations

	// Delete Operations
}
