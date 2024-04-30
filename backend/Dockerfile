# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight base image for the final image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the build stage to the container
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
