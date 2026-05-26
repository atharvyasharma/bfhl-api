# ── Stage 1: Build ────────────────────────────────────────────────────────────
# Use the official Maven image — no wrapper JAR needed
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy POM first so dependency layer is cached separately from source
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn package -DskipTests -B

# ── Stage 2: Run ──────────────────────────────────────────────────────────────
# Slim JRE-only image for the final container
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/bfhl-api.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
