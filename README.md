# Full-Stack Boilerplate: Angular & Spring Boot with MongoDB

This is a boilerplate project for a full-stack application using Angular 19 for the frontend, Spring Boot for the backend, and MongoDB as the database.

## Project Structure

- `/frontend`: Angular 19 application
- `/backend`: Spring Boot application with MongoDB integration

## Requirements

- Node.js (for frontend)
- Java 17 or higher (for backend)
- Maven
- MongoDB

## Frontend Setup

Navigate to the `/frontend` directory and run:

```bash
npm install
npm start
```

The Angular application will be available at `http://localhost:4200`.

## Backend Setup

1. Ensure MongoDB is running locally on port 27017 (default)

2. Navigate to the `/backend` directory and run:

```bash
mvn clean install
mvn spring-boot:run
```

The Spring Boot server will start on `http://locqalhost:8080`.

## Development in VSCode

This project is optimized for development with Visual Studio Code:

1. Install recommended extensions:

   - Angular Language Service
   - Spring Boot Extension Pack
   - MongoDB for VS Code

2. Open the project root directory in VSCode to work with both frontend and backend

3. Use the integrated terminal to run commands for each part of the application

## Communication Between Frontend and Backend

- The frontend is configured to communicate with the backend at `http://localhost:8080/api`
- CORS is configured on the backend to allow requests from the frontend origin

## Additional Documentation

- For more details on the frontend, see the [frontend README](/frontend/README.md)
- For more details on the backend, see the [backend README](/backend/README.md)
