package com.ecommerce.controllers;

import java.lang.constant.Constable;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.entities.Product;
import com.ecommerce.services.AddressService;
import com.ecommerce.services.ProductService;
import com.ecommerce.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private ProductService productService;
	private AddressService addrService;

	static final int GST_PERCENTAGE = 18;
	static final int DELIVERY_CHARGES = 45;

	public UserController(UserService userService, ProductService productService, AddressService addrService) {
		this.userService = userService;
		this.productService = productService;
		this.addrService = addrService;
	}

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
	public String purchased(@PathVariable("productId") String productId, Model model, Principal principal) {

		Product prod = productService.getProdById(productId);

		List<Product> products = (prod == null) ? null : List.of(prod);

//		addrService.getAddrByUserId(userId);

		if (products != null) {

			double total;
			double subTotal = prod.getProductPrice();
			Double gstValue = calculatePercentage(DELIVERY_CHARGES, subTotal);

			model.addAttribute("delivery_charges", DELIVERY_CHARGES);
			model.addAttribute("gst-percentage", DELIVERY_CHARGES);

		}

		model.addAttribute("products", products);

		return "purchesed";
	}

	private double calculatePercentage(double percentage, double value) {
		return (percentage / 100) * value;
	}
}
