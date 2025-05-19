# Todo Application

This is a Spring Boot application for managing todos.

## Running the Application

The application runs on port 8091. After starting the application, you can access it at:

```
http://localhost:8091
```

## API Documentation

### Swagger UI

You can access the Swagger UI documentation for this application at:

```
http://localhost:8091/swagger-ui/index.html
```

This provides an interactive interface to explore and test the API endpoints.

### API Information Page

For a more user-friendly overview of the API endpoints, you can visit:

```
http://localhost:8091/api-info
```

This page provides information about:
- How to access the Swagger UI
- The available Todo API endpoints

## Todo API Endpoints

This application primarily uses MVC controllers to serve HTML views rather than REST API endpoints. You can interact with the Todo functionality through the following URLs:

| URL | HTTP Method | Description |
|-----|-------------|-------------|
| /list-todos | GET | Lists all todos for the logged-in user |
| /add-todo | GET | Shows the page to add a new todo |
| /add-todo | POST | Adds a new todo |
| /delete-todo?id={id} | GET | Deletes a todo with the specified ID |
| /update-todo?id={id} | GET | Shows the page to update a todo with the specified ID |
| /update-todo | POST | Updates a todo |

## Authentication

The application uses form-based authentication. You can register a new user or log in with an existing user.

- Login page: http://localhost:8091/login
- Registration page: http://localhost:8091/register

The API information page and Swagger UI are accessible without authentication.