package com.satvik.restaurantapp.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Declares a common base class , w/o any table associated with it.
 * Add common fields here.
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp 
	private LocalDate creationDate;
	@UpdateTimestamp 
	private LocalDateTime lastUpdated;

}
