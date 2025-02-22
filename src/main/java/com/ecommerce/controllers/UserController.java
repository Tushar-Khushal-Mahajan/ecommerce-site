package com.ecommerce.controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.dtos.PurchasedItemDto;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.BuyProduct;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.services.AddressService;
import com.ecommerce.services.BuyProductService;
import com.ecommerce.services.ProductService;
import com.ecommerce.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private ProductService productService;
	private AddressService addrService;
	private BuyProductService buyService;

	static final int GST_PERCENTAGE = 18;
	static final int DELIVERY_CHARGES = 45;

	public UserController(UserService userService, ProductService productService, AddressService addrService,
			BuyProductService buyService) {
		this.userService = userService;
		this.productService = productService;
		this.addrService = addrService;
		this.buyService = buyService;
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

		List<Address> addresses = userService.getUserByUsername(principal.getName()).getAddress();

		if (prod != null) {

			double total;
			double subTotal = prod.getProductPrice();
			Double gstValue = calculatePercentage(GST_PERCENTAGE, subTotal);

			total = subTotal + gstValue + DELIVERY_CHARGES;

			model.addAttribute("sub_total", subTotal);
			model.addAttribute("total", total);
			model.addAttribute("delivery_charges", DELIVERY_CHARGES);
			model.addAttribute("gst_value", gstValue);
			model.addAttribute("gst_percentage", GST_PERCENTAGE);
			model.addAttribute("addrs", addresses);
		}

		model.addAttribute("products", products);

		return "purchesed";
	}

	private double calculatePercentage(double percentage, double value) {
		return (percentage / 100) * value;
	}

	@PostMapping("/saveAddr")
	public String saveAddress(@RequestParam("mobile") String mobile, @RequestParam("address") String addr,
			Principal principal, HttpServletRequest request) {

		User user = userService.getUserByUsername(principal.getName());

		Address savedAddress = addrService.saveAddress(Address.builder().addr(addr).mobile(mobile).user(user).build());

		String referer = request.getHeader("Referer");
		return "redirect:" + (referer != null ? referer : "/");
	}

	@GetMapping("/product/payment")
//	@ResponseBody
	public String payment() {
//		return "This is payment page";
		return "buyed";
	}

	@GetMapping("/buyProduct/{productId}/addr/{addrId}")
	public String successBuyed(@PathVariable("productId") String productId, @PathVariable("addrId") String addrId,
			Model model, Principal principal) {

		Product product = productService.getProdById(productId);
		Address addr = addrService.getAddrById(addrId);

		if (product != null && addr != null) {

			double total;
			double subTotal = product.getProductPrice();
			Double gstValue = calculatePercentage(GST_PERCENTAGE, subTotal);

			total = subTotal + gstValue + DELIVERY_CHARGES;

			System.out.println("TOTAL : " + total);

//			-------
			String name = principal.getName();
			User user = userService.getUserByUsername(name);

			BuyProduct buyProduct = BuyProduct.builder().id(UUID.randomUUID().toString()).address(addr).product(product)
					.totalPrice(total).user(user).build();

			buyService.save(buyProduct);

			List<PurchasedItemDto> purchasedItem = List.of(PurchasedItemDto.builder().orderId(buyProduct.getId())
					.productName(product.getProductName()).price(total).build());

			model.addAttribute("items", purchasedItem);

			return "buyed";

		} else {
			return null;
		}

	}
}
