package com.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.entities.User;
import com.ecommerce.services.ProductService;
import com.ecommerce.services.UserService;

@Controller
public class HomeController {

	private UserService service;
	private ProductService productService;

	public HomeController(UserService service, ProductService productService) {
		this.service = service;
		this.productService = productService;
	}

	@GetMapping("")
	public String sayHello() {
		User user = service.getUserByUsername("tushar@gmail.com");
		System.out.println(user);
		return "home";
	}

	@GetMapping("/product")
	public String showProductPage() {

		return "product";
	}
}
