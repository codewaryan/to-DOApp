# Use Java 21 runtime
FROM eclipse-temurin:21-jdk AS build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml for caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the JAR
RUN ./mvnw clean package -DskipTests

# Expose port (optional)
EXPOSE 8080

# Run the app using Render's $PORT
ENV PORT 8080
CMD java -jar target/Smart-To-Do-List-0.0.1-SNAPSHOT.jar --server.port=$PORT
