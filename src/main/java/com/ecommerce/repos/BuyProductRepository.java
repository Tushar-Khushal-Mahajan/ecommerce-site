package com.ecommerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.BuyProduct;

public interface BuyProductRepository extends JpaRepository<BuyProduct, String> {

}
