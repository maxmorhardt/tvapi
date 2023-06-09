# Build
FROM openjdk:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean install

# Run
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/tvapi-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "tvapi-0.0.1-SNAPSHOT.jar"]