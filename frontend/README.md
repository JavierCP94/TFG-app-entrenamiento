# Angular Frontend

This is the frontend application built with Angular 19.2.9 and TailwindCSS.

## Requirements

- Node.js (LTS version recommended)
- npm (comes with Node.js)

## Setup Instructions

1. Install dependencies:
   ```
   npm install
   ```

2. Start the development server:
   ```
   npm start
   ```

3. Open your browser and navigate to `http://localhost:4200`

## Project Structure

- `src/app/core`: Core functionality (services, guards, interceptors)
- `src/app/shared`: Shared components, models, and utilities
- `src/app/features`: Feature modules (lazy-loaded)
- `src/environments`: Environment configuration

## Available Scripts

- `npm start`: Start the development server
- `npm run build`: Build the application for production
- `npm run watch`: Build and watch for changes
- `npm test`: Run unit tests
- `npm run lint`: Run linting

## Connecting to Backend

The application is configured to connect to the backend API at `http://localhost:8080/api`. You can change this configuration in the environment files:

- `src/environments/environment.ts`
- `src/environments/environment.development.ts`
- `src/environments/environment.production.ts`