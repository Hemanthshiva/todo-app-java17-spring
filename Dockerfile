# Build stage
FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Create directory for SQLite DB
VOLUME /data

# Set environment variable so Spring picks up volume path
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/data/todo.db

# Expose port
EXPOSE 8091

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
