package com.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.User;
import com.ecommerce.enums.Role;
import com.ecommerce.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService service;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/register")
	@ResponseBody
	public String register() {

		String encodePassword = encoder.encode("tushar123");

		service.saveUser(User.builder().name("Bhuvan").username("bhuvan@gmail.com").password(encodePassword)
				.role(Role.USER).build());

		System.out.println("role of user = " + Role.USER);
		return "login page";
	}
}
