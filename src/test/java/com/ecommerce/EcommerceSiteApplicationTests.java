package com.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.entities.User;
import com.ecommerce.services.UserService;

@SpringBootTest
class EcommerceSiteApplicationTests {

	@Autowired
	private UserService service;

	@Test
	void contextLoads() {
	}

}
