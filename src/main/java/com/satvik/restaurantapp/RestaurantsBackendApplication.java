package com.satvik.restaurantapp;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantsBackendApplication.class, args);
	}
	@Bean
	ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration() //get default config
		.setPropertyCondition(Conditions.isNotNull()) //transfer only not null props from src-> dest
		.setMatchingStrategy(MatchingStrategies.STRICT);//transfer the props form src -> dest which match by name & data type
	
		return mapper;
	}
}
