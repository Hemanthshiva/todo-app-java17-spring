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

## Docker Support

This application can be containerized and run using Docker. Below are instructions for building, running, and deploying the application with Docker.

### Prerequisites

- Docker installed on your machine

### Building the Docker Image

Build the Docker image:
```bash
docker build -t todo-app .
```

The Dockerfile uses a multi-stage build process that automatically compiles the application, so you don't need to build the JAR file separately.

### Running Locally with Docker

Run the application as a standalone container:

```bash
docker run -d -p 8091:8091 -v todo-data:/data --name todo-app-container todo-app
```

This command:
- Runs the container in detached mode (`-d`)
- Maps port 8091 on your host to port 8091 in the container
- Creates a named volume `todo-data` mounted at `/data` in the container for database persistence
- Names the container `todo-app-container`

Access the application at:
```
http://localhost:8091
```

### Deploying with Docker Compose

For a more production-like setup, you can use Docker Compose. Create a file named `docker-compose.yml` with the following content:

```yaml
version: '3.8'

services:
  todo-app:
    build: .
    ports:
      - "8091:8091"
    volumes:
      - todo-data:/data
    restart: unless-stopped

volumes:
  todo-data:
```

Then run:

```bash
docker-compose up -d
```

To stop the services:

```bash
docker-compose down
```

### Managing the Docker Container

- To stop the container:
  ```bash
  docker stop todo-app-container
  ```

- To start an existing container:
  ```bash
  docker start todo-app-container
  ```

- To view logs:
  ```bash
  docker logs todo-app-container
  ```

- To remove the container:
  ```bash
  docker rm todo-app-container
  ```

### Database Persistence

The application uses SQLite with the database file stored in a Docker volume. This ensures your data persists even if the container is removed. The database file is stored at `/data/todo.db` inside the container.
