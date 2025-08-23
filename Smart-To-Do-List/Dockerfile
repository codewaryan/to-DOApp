# Use an official JDK runtime as base image
# Use Java 21 runtime
FROM eclipse-temurin:21-jdk AS build

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for dependency caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (will be cached unless pom.xml changes)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Package the application
RUN ./mvnw clean package -DskipTests

# Expose port (Render sets PORT dynamically, so this is optional for docs)
EXPOSE 8080

# Run the app (Render provides $PORT env variable)
ENTRYPOINT ["java","-jar","target/*.jar","--server.port=${PORT}"]
