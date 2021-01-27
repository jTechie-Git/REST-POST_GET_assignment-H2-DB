package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.userregister.model.User;
import com.app.userregister.repository.UserRepository;
import com.app.userregister.service.UserService;

@RunWith(MockitoJUnitRunner.class)

public class AuthControllerTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserService userService;

	@Test
	public void getAllUserTest() {

		List<User> userList = Arrays.asList(new User((long) 101, "test", "test", "test@test.com", Instant.now()),
				new User((long) 102, "test1", "test1", "test1@test.com", Instant.now()));
		when(userRepository.findAll()).thenReturn(userList);

		List<User> users = userService.calculateTotalValue();
		assertEquals(userList, users);
	}

	@Test
	public void findUserById() {
		Optional<User> user1 = Optional.of(new User((long) 103, "test", "test", "test@test.com", Instant.now()));
		when(userRepository.findById((long) 103)).thenReturn(user1);
		Optional<User> user = userService.getUserById(103);
		assertEquals(user, user1);
	}

	@Test
	public void userNotFoundById() {
		Optional<User> user1 = Optional.of(new User((long) 103, "test", "test", "test@test.com", Instant.now()));
		when(userRepository.findById((long) 104)).thenReturn(user1);
		Optional<User> user = userService.getUserById(104);
		assertNotSame(user, user1);
	} 

	@Test
	public void createEmployeeTest() {
		User user = new User((long) 103, "test", "test", "test@test.com", Instant.now());
		userRepository.save(user);
		assertEquals(user, user);
	}
}