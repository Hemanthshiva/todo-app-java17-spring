# Use a base JDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the project JAR (build first!)
COPY target/*.jar app.jar

# Create directory for SQLite DB
VOLUME /data

# Set environment variable so Spring picks up volume path
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/data/todo.db

# Expose port
EXPOSE 8091

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
