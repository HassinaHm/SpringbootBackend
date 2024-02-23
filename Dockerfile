# Stage 1: Build Spring Boot application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal image with JRE and Spring Boot application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/your-application.jar /app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
