package com.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ecommerce.entities.Address;
import com.ecommerce.repos.AddressRepository;

@Service
public class AddressService {

	private AddressRepository addrRepo;

	public AddressService(AddressRepository addrRepo) {
		this.addrRepo = addrRepo;
	}

	public Address saveAddress(Address address) {
		address.setAddrId(UUID.randomUUID().toString());
		return addrRepo.save(address);
	}

	public List<Address> getAddrByUserId(String userId) {
		return addrRepo.findByUser_UserId(userId);
	}

	public List<Address> getAddrByUserName(String username) {
		return addrRepo.findByUser_Username(username);
	}

}
