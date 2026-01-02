package com.satvik.restaurantapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satvik.restaurantapp.dto.ApiResponse;
import com.satvik.restaurantapp.dto.RestaurantDTO;
import com.satvik.restaurantapp.entities.Restaurant;
import com.satvik.restaurantapp.service.RestaurantService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/restaurants")
public class RestaurantController {
	private static final Logger log =  LoggerFactory.getLogger(RestaurantController.class);
	@Autowired
	private RestaurantService restaurantService;


	/*
	 * URL - http://host:port/restaurants , method=GET payload - none Resp - List of
	 * Restaurants -> Json[] -> clnt
	 */
	@GetMapping
	public ResponseEntity<?> getAvailableRestaurants() {
		List<Restaurant> list = restaurantService.getAllAvailableRestaurants();
        log.info("Fetching all restaurants");

		if (list.isEmpty()) {
			// sts code SC 204
            log.warn("No restaurants found");

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			// SC 204
		}
		// non empty
		return ResponseEntity.ok(list);
		// SC 200 , body - list
	}

	/*
	 * Desc - add new restaurant URL - http://host:port/restaurants Method - POST
	 * Resp -success - SC 201 + success mesg 
	 * SC 400 + err mesg
	 */	
	@PostMapping
	public ResponseEntity<?> addNewRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

			// invoke service layer method
		 	log.info("Adding new restaurant");
			ApiResponse message = restaurantService.addRestaurant(restaurantDTO);
			//=> success
	        log.info("Restaurant added successfully");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(message);
		
		
	}
	/*
	 * Desc -Soft delete  existing  restaurant .
	 * URL - http://host:port/restaurants/{restaurantId}    Method - DELETE
	 * Resp - success - SC 200 + success mesg 
	 * Failure - SC 404 
	 */
	@DeleteMapping("/{restaurantId}")
	public ResponseEntity<?> deleteRestaurantDetails(@PathVariable Long restaurantId)
	{
	    log.info("Request received to delete restaurant");
	    log.debug("RestaurantId to delete: {}", restaurantId);
		ApiResponse  message = restaurantService.deleteDetails(restaurantId);
	    log.info("Restaurant deleted successfully, id={}", restaurantId);

		return ResponseEntity.status(HttpStatus.OK).body(message);
		
	}
	/*
	 *
	 * 1. Get Existing restaurant details
	 * URL - http://host:port/restaurants/{restaurantId}    
	 * Method - GET
	 * Resp - success - SC 200 + Restaurant -> JSON
	 * Failure - SC 404 + API Response DTO
	 */
	@GetMapping("/{restaurantId}")
	public ResponseEntity<?> getRestaurantDetails(@PathVariable Long restaurantId)
	{
	    log.info("Fetching restaurant details");
	    log.debug("RestaurantId={}", restaurantId);


	    Restaurant restaurant =
	            restaurantService.getRestaurantDetails(restaurantId);

	    log.info("Restaurant fetched successfully, id={}", restaurantId);

	    return ResponseEntity.ok(restaurant);
	}
	/*
	 *
	 * 2. Update Existing restaurant details
	 * URL - http://host:port/restaurants/{restaurantId}    
	 * Method - PUT
	 * path var - restaurantId (validation)
	 * Payload -updated restaurant details
	 * Resp - success - SC 200 +  API Response DTO - success
	 * Failure - SC 404 | 400  + API Response DTO
	 */
	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> updateRestaurantDetails(@PathVariable Long restaurantId,@RequestBody RestaurantDTO restaurantDTO)
	{


	    log.info("Request received to update restaurant");
	    log.debug("RestaurantId={}, UpdatePayload={}", restaurantId, restaurantDTO);

	    ApiResponse message =
	            restaurantService.updateDetails(restaurantId, restaurantDTO);

	    log.info("Restaurant updated successfully, id={}", restaurantId);

	    return ResponseEntity.ok(message);
	}
	@PutMapping("/{restaurantId}/restore")
	public ResponseEntity<ApiResponse> restoreRestaurant(
	        @PathVariable Long restaurantId) {

	    log.info("Restore restaurant request, id={}", restaurantId);

	    ApiResponse response =
	            restaurantService.restoreRestaurant(restaurantId);

	    log.info("Restaurant restored successfully, id={}", restaurantId);

	    return ResponseEntity.ok(response);
	}

}
