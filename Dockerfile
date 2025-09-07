# ---- Build stage ----
FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app

# Copy pom and source
COPY pom.xml .
COPY src ./src

# Package application (skip tests for faster builds if needed)
RUN mvn clean package -DskipTests

# ---- Runtime stage ----
FROM eclipse-temurin:23-jre
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
