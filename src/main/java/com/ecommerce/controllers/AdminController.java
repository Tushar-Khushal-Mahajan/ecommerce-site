package com.ecommerce.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entities.Product;
import com.ecommerce.services.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private ProductService productService;

	public AdminController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public String admin(Model model) {
		model.addAttribute("product", new Product());
		return "admin/add-prod";
	}

	@GetMapping("/add-product")
	public String addProducts(Model model) {

		model.addAttribute("product", new Product());
		return "admin/add-prod";
	}

	@PostMapping("/Saveproduct")
	@ResponseBody
	public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("img") MultipartFile file)
			throws IOException {

		System.out.println("SAVED PRODUCT = :" + product);

		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getBytes());

//		if (productService.insertProduct(product) != null) {
//			// SUCCESS
		return "product save";
//		} else {
//			// SOMETHING WRONG
//			return "something went wrong";
//		}

	}
}
