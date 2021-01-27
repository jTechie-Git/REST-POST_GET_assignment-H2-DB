package com.app.userregister.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.userregister.model.User;
import com.app.userregister.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> calculateTotalValue() {

		List<User> userList = repository.findAll();

		return userList;
	}

	public Optional<User> getUserById(int id) {
		return repository.findById((long) id);
	}

}
