package com.ecommerce.services;

import java.sql.Savepoint;

import org.springframework.stereotype.Service;

import com.ecommerce.entities.BuyProduct;
import com.ecommerce.repos.BuyProductRepository;

@Service
public class BuyProductService {

	private BuyProductRepository buypRepository;

	public BuyProductService(BuyProductRepository buypRepository) {
		this.buypRepository = buypRepository;
	}

//	-------------

	public BuyProduct save(BuyProduct product) {
		return buypRepository.save(product);
	}

}
