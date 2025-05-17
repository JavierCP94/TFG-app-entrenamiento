# Spring Boot Backend

This is the backend application built with Spring Boot and MongoDB.

## Requirements

- Java 17 or higher
- Maven
- MongoDB

## Setup Instructions

1. Ensure MongoDB is running locally on port 27017 (default)

2. Build the application:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

4. The server will start on `http://localhost:8080`

## Project Structure

- `src/main/java/com/example/springbackend/config`: Configuration classes
- `src/main/java/com/example/springbackend/controller`: REST controllers
- `src/main/java/com/example/springbackend/model`: Data models
- `src/main/java/com/example/springbackend/repository`: MongoDB repositories
- `src/main/java/com/example/springbackend/service`: Business logic services
- `src/main/java/com/example/springbackend/exception`: Exception handling

## API Endpoints

- Health check: `GET /api/health`
- Additional endpoints can be added as needed

## Configuration Profiles

- `application.properties`: Default configuration
- `application-dev.properties`: Development configuration
- `application-prod.properties`: Production configuration

To run with a specific profile:
```
mvn spring-boot:run -Dspring.profiles.active=dev
```

## MongoDB Setup

The application is configured to connect to a MongoDB database named `app_db` on `localhost:27017`. If you need to change these settings, update the `application.properties` file.