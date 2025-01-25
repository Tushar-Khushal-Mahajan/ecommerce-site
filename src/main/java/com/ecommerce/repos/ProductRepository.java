package com.ecommerce.repos;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.entities.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
