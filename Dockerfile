# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot jar to the container
COPY target/*.jar app.jar

# Copy the environment file to the container
COPY src/main/resources/env/.env .env

# Expose the port Spring Boot runs on
EXPOSE 8080

# Command to run the application with environment variables
ENTRYPOINT ["java", "-jar", "--spring.config.location=file:.env", "app.jar"]
