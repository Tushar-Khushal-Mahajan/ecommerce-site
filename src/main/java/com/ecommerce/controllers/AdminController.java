package com.ecommerce.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entities.Product;
import com.ecommerce.helper.FileHelper;
import com.ecommerce.services.AmazonS3Service;
import com.ecommerce.services.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private ProductService productService;
	private AmazonS3Service s3Service;
	private String bucketUrl;

	public AdminController(ProductService productService, AmazonS3Service s3Service,
			@Value("${bucket.url}") String bucketUrl) {
		this.productService = productService;
		this.s3Service = s3Service;
		this.bucketUrl = bucketUrl;
	}

	@GetMapping
	public String admin(Model model) {
		return "redirect:admin/add-product";
	}

	@GetMapping("/add-product")
	public String addProducts(@RequestParam(name = "status", required = false, defaultValue = "EMPTY") String status,
			Model model) {

		System.out.println("status = :" + status);

		model.addAttribute("STATUS", status);
		model.addAttribute("product", new Product());
		return "admin/add-prod";
	}

	@PostMapping("/Saveproduct")
	public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("img") MultipartFile file,
			Model model) throws IOException {

		StringBuffer parameter = new StringBuffer("EMPTY");

		try {

			if (file.isEmpty()) {
				throw new Exception("File Object is empty");
			}

			System.out.println("SAVED PRODUCT = :" + product);

			System.out.println("empty :" + file.isEmpty());
			// for s3
			FileHelper fileHelper = new FileHelper(s3Service);
			String fileName = fileHelper.saveImageAndReturnImageNameInS3(file);
			// After successful storing the file save product obj with fileName

			System.out.println("Bucket url :" + bucketUrl);
			product.setProductImage(bucketUrl + fileName);
			if (productService.insertProduct(product) != null) {
				// SUCCESS
				parameter.setLength(0);
				parameter.append("SAVE");

			} else {
				// SOMETHING WRONG
				parameter.setLength(0);
				parameter.append("ERR");
			}
		} catch (Exception e) {
			e.printStackTrace();
			parameter.setLength(0);
			parameter.append("ERR");
		}

		return "redirect:add-product?status=" + parameter.toString();

	}
	
	
	@GetMapping("/manageProds")
	public String manageProducts() {
		return "admin/manage-prods";
	}
}
