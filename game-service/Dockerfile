FROM --platform=linux/amd64 eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

FROM --platform=linux/amd64 eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/game-service-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
