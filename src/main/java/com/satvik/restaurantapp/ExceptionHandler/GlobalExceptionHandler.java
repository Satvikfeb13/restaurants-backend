package com.satvik.restaurantapp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.satvik.restaurantapp.custom_excpetions.ResourceAlreadyExists;
import com.satvik.restaurantapp.custom_excpetions.ResourceNotFoundException;
import com.satvik.restaurantapp.dto.ApiResponse;

@RestControllerAdvice

public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceAlreadyExists.class)
	ResponseEntity<?>getResourceAlreadyExists(ResourceAlreadyExists ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?>getResourceNotFoundException(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	   @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ApiResponse> handleRuntime(RuntimeException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(new ApiResponse(ex.getMessage(), "error"));
	    }
}
