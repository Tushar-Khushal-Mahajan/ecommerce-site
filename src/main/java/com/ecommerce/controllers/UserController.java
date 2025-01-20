package com.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/")
	public String sayHello() {

		return "home";
	}

	@GetMapping("/product")
	public String showProductPage() {

		return "product";
	}

	@GetMapping("/me")
	public String meSection() {

		return "me";
	}

	@GetMapping("/purchase")
	public String purchased() {

		return "purchesed";
	}
}
