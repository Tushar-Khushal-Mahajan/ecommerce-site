package com.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.entities.Address;
import com.ecommerce.repos.AddressRepository;

@Service
public class AddressService {

	private AddressRepository addrRepo;

	public AddressService(AddressRepository addrRepo) {
		this.addrRepo = addrRepo;
	}

	public List<Address> getAddrByUserId(String userId) {
		return addrRepo.findByUser_UserId(userId);
	}

}
