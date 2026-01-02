package com.satvik.restaurantapp.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
		// add exception handling method - to handleP.L validation failure - for req body (JSON payload)

		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
			System.out.println("in handle @Valid ");
			//1. get list of rejected fields
			List<FieldError> fieldErrors = e.getFieldErrors();
			//2. Covert it to Map <Key - field Name , Value - err mesg>
			Map<String, String> errorFieldMap = fieldErrors.stream() //Stream<FieldError>
			.collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));//f -> f.getField(), f -> f.getDefaultMessage()
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorFieldMap);
		}
}
