
# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Cache Maven dependencies
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Build the JAR
COPY src ./src
RUN mvn -q clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

WORKDIR /app

# Create a non-root user
RUN useradd -m appuser

# Copy the generated JAR
COPY --from=build /app/target/*.jar app.jar

# Run as a non-root user
USER appuser

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
