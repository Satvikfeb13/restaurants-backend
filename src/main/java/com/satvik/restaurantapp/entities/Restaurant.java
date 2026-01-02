package com.satvik.restaurantapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Restaurant extends BaseEntity {
	@Column(length = 100, unique = true)
	private String name;
	private String address;
	@Column(length = 20)
	private String city;
	private String description;	
	private boolean status;

	public Restaurant(String name, String address, String city, String description) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.description = description;
		this.status=true;
	}

}
