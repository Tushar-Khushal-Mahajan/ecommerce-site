package com.ecommerce.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "product")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {

	@Id
	private String productId;
	private String productName;
	private String productDescription;
	private int productPrice;
	private String productImage;
	private String productCategory;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<User>();

}
