package com.satvik.restaurantapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
	@NotBlank(message = "Restautant name can not be empty")
	private String name;
	@NotBlank(message = "address can not be blank")
	private String address;
	@Column(length = 20)
	@NotBlank
	private String city;
	@NotNull(message = "Description can not bee empty")
	private String description;	

}
