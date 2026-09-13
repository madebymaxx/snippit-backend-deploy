# Build stage
FROM maven:3.9-eclipse-temurin-25 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Maven configuration first
# This improves Docker layer caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy application source code
COPY src src

# Build the Spring Boot executable JAR
RUN ./mvnw clean package -DskipTests


# Runtime stage
FROM eclipse-temurin:25-jre

# Set the working directory
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Render uses the PORT environment variable
EXPOSE 10000

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]