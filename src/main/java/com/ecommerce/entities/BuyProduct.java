package com.ecommerce.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "buy_products")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyProduct {

	@Id
	private String id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	private Address address;

	private double totalPrice;

}
