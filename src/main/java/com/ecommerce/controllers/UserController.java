package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	@ResponseBody
	public String user() {
		return "Hello this is user";
	}

	@GetMapping("/me")
	public String meSection() {

		return "me";
	}

	@GetMapping("/product/purchase/{productId}")
	public String purchased(@PathVariable("productId") String productId) {

		return "purchesed";
	}
}
