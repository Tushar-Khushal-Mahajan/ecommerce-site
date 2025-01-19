package com.ecommerce.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.repos.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository repository;

	public CustomUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found :" + username));
	}

}
