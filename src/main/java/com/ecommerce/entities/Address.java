package com.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "addr")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Address {

	@Id
	private String addrId;
	private String addr;
	private String mobile;

	@ManyToOne
	private User user;
}
