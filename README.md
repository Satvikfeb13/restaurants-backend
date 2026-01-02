🍽️ Restaurant Management Backend API
A Spring Boot RESTful backend application for managing restaurant data.
The system provides CRUD operations with soft delete & restore, DTO-based validation, custom exception handling, and clean layered architecture.

✨ Key Features

Create new restaurants

Fetch all active restaurants

Fetch restaurant details by ID

Update restaurant information

Soft delete restaurants using status flag

Restore soft-deleted restaurants

DTO-based request validation

Centralized exception handling

Transaction management

Structured logging using SLF4J

🏗️ Architecture Overview

The application follows a layered architecture:

Controller → Service → Repository (DAO) → Database


Controller: Exposes REST APIs

Service: Contains business logic and transaction boundaries

Repository (DAO): Handles database operations using Spring Data JPA

DTOs: Ensure validation and safe data transfer

Entities: Map domain objects to database tables

Exception Handler: Centralized error handling across the application

🛠️ Tech Stack

Java

Spring Boot

Spring Web (REST APIs)

Spring Data JPA

Hibernate

Jakarta Validation

ModelMapper

Lombok

Maven

MySQL

📁 Project Structure
```text
com.satvik.restaurantapp
│
├── controller
│   └── RestaurantController.java
│
├── service
│   ├── RestaurantService.java
│
├── dao
│   └── RestaurantDao.java
│
├── entities
│   ├── BaseEntity.java
│   └── Restaurant.java
│
├── dto
│   ├── RestaurantDTO.java
│   └── ApiResponse.java
│
├── custom_excpetions
│   ├── ResourceAlreadyExists.java
│   └── ResourceNotFoundException.java
│
├── ExceptionHandler
│   └── GlobalExceptionHandler.java
│
└── RestaurantsBackendApplication.java
```


🔗 API Endpoints
 Base URL - http://localhost:8080(PortNumber)
```text
 HTTP Method  Endpoint                     Description                   
-----------------------------------------------------------------------  
 GET          /restaurants                 Get all active restaurants      
 POST         /restaurants                 Add a new restaurant          
 GET          /restaurants/{id}            Get restaurant by ID          
 PUT          /restaurants/{id}            Update restaurant details     
 DELETE       /restaurants/{id}            Soft delete a restaurant      
 PUT          /restaurants/{id}/restore    Restore a deleted restaurant  
```

📥 Sample Request JSON
Add Restaurant (POST /restaurants)
{
  "name": "Food Plaza",
  "address": "MG Road",
  "city": "Pune",
  "description": "Multi-cuisine restaurant"
}

📤 Sample Success Response
{
  "message": "Restautant added id 1",
  "status": "Success",
  "timeStamp": "2026-01-02T15:20:10"
}

❌ Error & Validation Response Examples
Duplicate Restaurant
Restaurant with same name already exists !!!

Resource Not Found
Invalid restaurant id

Validation Errors
{
  "name": "Restautant name can not be empty",
  "address": "address can not be blank"
}

🗃️ Database Schema Overview
Table: restaurants
Column	Description
id	Primary key
name	Unique restaurant name
address	Restaurant address
city	City
description	Description
status	Active / Deleted (soft delete)
creation_date	Auto-generated
last_updated	Auto-generated
⚙️ Setup & Run Instructions
1️⃣ Clone the repository
git clone https://github.com/your-username/restaurants-backend.git

2️⃣ Navigate to the project
cd restaurants-backend

3️⃣ Configure database (application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4️⃣ Run the application
mvn spring-boot:run


📍 Server runs at:

http://localhost:8080

🧠 Key Backend Concepts & Best Practices Used

Layered architecture

DTO pattern

Soft delete strategy

Custom runtime exceptions

Global exception handling (@RestControllerAdvice)

Transaction management (@Transactional)

Derived query methods (Spring Data JPA)

Validation using Jakarta Bean Validation

Logging with SLF4J

ModelMapper with strict matching

👨‍💻 Author

Satvik Patil
Aspiring Java Full Stack Developer

🔗 GitHub: https://github.com/Satvikfeb13

⭐ If you find this project useful, consider giving it a star on GitHub!
