package com.ecommerce.services;

import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.User;
import com.ecommerce.repos.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	// ---------------------

	public User getUserByUsername(String username) {
		return repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found :" + username));
	}

	public User saveUser(User user) {

		user.setUserId(UUID.randomUUID().toString());
		return repository.save(user);
	}
}
