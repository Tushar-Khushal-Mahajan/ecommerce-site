package com.ecommerce.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.entities.Product;
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

	@GetMapping
	public String sayHello() {
		User user = service.getUserByUsername("tushar@gmail.com");
		System.out.println(user);
		return "home";
	}

	@GetMapping("/product/view/{productId}")
	public String showProductPage(@PathVariable("productId") String productId, Model model) {

		Product product = productService.getProdById(productId);
		List<Product> allProducts = productService.getAllProducts();

		model.addAttribute("product", product);
		model.addAttribute("allProducts", allProducts);

//		System.out.println("Product ID :" + productId + product);

		return "product";
	}
}
