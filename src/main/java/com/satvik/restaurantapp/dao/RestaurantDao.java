package com.satvik.restaurantapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satvik.restaurantapp.dto.RestaurantDTO;
import com.satvik.restaurantapp.entities.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant,Long> {
//add a finder method to list all AVAILABLE (status=true) restaurants
	List<Restaurant> findByStatusTrue();
	//check if restaurant with same name already exists ! - derived query
	boolean existsByName(String restaurantName);
	
}
