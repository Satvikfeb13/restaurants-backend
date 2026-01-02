package com.satvik.restaurantapp.custom_excpetions;

public class ResourceAlreadyExists extends RuntimeException {
	public ResourceAlreadyExists(String errMesg) {
		super(errMesg);
	}
}
