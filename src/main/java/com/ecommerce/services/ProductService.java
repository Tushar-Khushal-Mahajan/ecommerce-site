package com.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.ResourceNotFound;
import com.ecommerce.repos.ProductRepository;

@Service
public class ProductService {

  private ProductRepository prodRepository;

  public ProductService(ProductRepository prodRepository) {
    this.prodRepository = prodRepository;
  }

  // ----------------------------

  public Product insertProduct(Product product) {
    product.setProductId(UUID.randomUUID().toString());

    return prodRepository.save(product);
  }

  public List<Product> getAllProducts() {
    return (List<Product>) prodRepository.findAll();
  }

  public Product getProdById(String prodId) {
    return prodRepository.findById(prodId)
        .orElseThrow(() -> new ResourceNotFound("Product with id not found :" + prodId));
  }

}
