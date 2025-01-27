package com.ecommerce.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

	List<Address> findByUser_UserId(String userId);
}
