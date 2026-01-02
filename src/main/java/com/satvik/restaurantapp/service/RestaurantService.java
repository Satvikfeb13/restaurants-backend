package com.satvik.restaurantapp.service;

import java.util.List;

import com.satvik.restaurantapp.dto.ApiResponse;
import com.satvik.restaurantapp.dto.RestaurantDTO;
import com.satvik.restaurantapp.entities.Restaurant;

public interface RestaurantService {
	List<Restaurant> getAllAvailableRestaurants();

	ApiResponse addRestaurant(RestaurantDTO restaurantDTO);

	ApiResponse deleteDetails(Long restaurantId);

	Restaurant getRestaurantDetails(Long restaurantId);

	ApiResponse updateDetails(Long restaurantId, RestaurantDTO restaurantDTO);

	ApiResponse restoreRestaurant(Long restaurantId);
}
