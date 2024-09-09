### service-r2
Alien Management Web Service

<br/>

## Description

This is a web service for managing alien entities, developed using Spring Boot and following a RESTful architecture. 
The service provides a set of REST endpoints to perform CRUD (Create, Read, Update, Delete) operations on alien data,
ensuring a robust and scalable solution.

<br/>

## Persistence Layer

The application uses Java Persistence API (JPA) for interacting with a relational database, providing a seamless way 
to handle database operations.
Transactions are managed to ensure data integrity and consistency during operations.

<br/>

## Security

The application implements simple API-key authentication to secure access to its endpoints,
ensuring that only authorized users can perform operations.

<br/>

## Logging

For logging various events and debugging purposes, the project utilizes Log4j, 
offering configurable and flexible logging capabilities.

<br/>

## Exception Handling

The service is equipped with a robust error-handling mechanism using @RestControllerAdvice and custom exceptions
to provide clear and meaningful error responses to the clients.

<br/>

## Containerization
To facilitate deployment and environment consistency, Dockerfiles are included in the project.
These Dockerfiles allow the application to be containerized, making it easy to deploy and run in various environments.

<br/>

## Service Discovery

The project uses Eureka for service discovery, enabling microservices within the system to find and communicate 
with each other efficiently, making the architecture more dynamic and resilient.

<br/>

## Libraries
- spring-boot-starter-web;
- spring-boot-starter-security;
- spring-boot-starter-data-jpa;
- mysql-connector-j;
- spring-boot-starter-test<;
- log4j-core;
- spring-cloud-starter-netflix-eureka-client;
- commons-lang3;
- lombok.