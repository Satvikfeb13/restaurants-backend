package com.satvik.restaurantapp.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.satvik.restaurantapp.custom_excpetions.ResourceAlreadyExists;
import com.satvik.restaurantapp.custom_excpetions.ResourceNotFoundException;
import com.satvik.restaurantapp.dao.RestaurantDao;
import com.satvik.restaurantapp.dto.ApiResponse;
import com.satvik.restaurantapp.dto.RestaurantDTO;
import com.satvik.restaurantapp.entities.Restaurant;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	private static final Logger log= LoggerFactory.getLogger(RestaurantServiceImpl.class);
	
	// depcy - dao i/f
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Restaurant> getAllAvailableRestaurants() {
	    log.info("Fetching all available restaurants");

	    List<Restaurant> restaurants = restaurantDao.findByStatusTrue();

	    log.debug("Available restaurants count={}", restaurants.size());

	    return restaurants;
	}

	@Override
	public ApiResponse addRestaurant(RestaurantDTO restaurantDTO) {
		// check if restaurant with same name already exists !
	    log.info("Add restaurant request, name={}", restaurantDTO.getName());
		if (restaurantDao.existsByName(restaurantDTO.getName())) {
			// dup restaurant name -> throw custom exception : unchecked
	        log.warn("Restaurant already exists with name={}", restaurantDTO.getName());

			throw new ResourceAlreadyExists("Restaurant with same name already exists !!!");
		}
	
	    log.debug("Mapping RestaurantDTO to Restaurant entity");

		
	    Restaurant restaurant = mapper.map(restaurantDTO, Restaurant.class);
		// => new restaurant name -> set status : true
	    restaurant.setStatus(true);
		// save the details

	    Restaurant persistentRestaurant = restaurantDao.save(restaurant);

	    log.info("Restaurant added successfully, id={}",
	             persistentRestaurant.getId());
		
		return new ApiResponse("Success", "Restautant added id "+persistentRestaurant.getId());
	}

	@Override
	public ApiResponse deleteDetails(Long restaurantId) {
		//validate id
	    log.info("Soft delete request for restaurantId={}", restaurantId);

		Restaurant restaurant=restaurantDao.findById(restaurantId)
			     .orElseThrow(() -> {
		                log.warn("Restaurant not found for delete, id={}", restaurantId);
		                return new ResourceNotFoundException("Invalid restaurant id !!!!!");
		            });
		//=> restaurant : persistent
		//setter - status : false
		restaurant.setStatus(false);
	    log.info("Restaurant soft deleted successfully, id={}", restaurantId);

		return new ApiResponse("Success","Soft deleted restaurant details....");
	} //no exc -> tx.commit-> DML - update -> session close

	@Override
	public Restaurant getRestaurantDetails(Long restaurantId) {
		   log.info("Fetching restaurant details, id={}", restaurantId);

		    Restaurant restaurant = restaurantDao.findById(restaurantId)
		            .orElseThrow(() -> {
		                log.warn("Restaurant not found, id={}", restaurantId);
		                return new ResourceNotFoundException("Restaurant id invalid !!!!");
		            });

		    log.debug("Restaurant fetched successfully, id={}", restaurantId);

		    return restaurant;
		}


	@Override
	public ApiResponse updateDetails(Long restaurantId, RestaurantDTO restaurantDTO) {
	    log.info("Update restaurant request, id={}", restaurantId);

		
		Restaurant existingRestuarant=getRestaurantDetails(restaurantId);
	    log.debug("Updating restaurant fields for id={}", restaurantId);

		//=> restaurant exists , existingRestuarant : persistent
		//invoke setters
		existingRestuarant.setName(restaurantDTO.getName());
		existingRestuarant.setAddress(restaurantDTO.getAddress());
		existingRestuarant.setCity(restaurantDTO.getCity());
	//	existingRestuarant.setStatus(restaurant.isStatus());
		existingRestuarant.setDescription(restaurantDTO.getDescription());	
	    log.info("Restaurant updated successfully, id={}", restaurantId);
	    
		return new ApiResponse("Restaurant Details Updated ...", "Success");
	}

	@Override
	public ApiResponse restoreRestaurant(Long restaurantId) {
		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(()->{
	                return new ResourceNotFoundException("Invalid restaurant id");
				});
		if(restaurant.isStatus()) {
			return  new ApiResponse("Restaurant is already active","Success");
		}
		restaurant.setStatus(true);
		 log.info("Restaurant restored successfully, id={}", restaurantId);

		    return new ApiResponse("Restaurant restored successfully","Success");
	}
	
	
	

}
