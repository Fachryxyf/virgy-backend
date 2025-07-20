# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Salin semua file project
COPY . .

# Build aplikasi dengan Spring Boot plugin
RUN mvn clean package -DskipTests

# Stage 2: Run (lighter image)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Salin JAR hasil build
COPY --from=builder /app/target/*.jar app.jar

# Ekspose port sesuai konfigurasi aplikasi (ubah jika perlu)
EXPOSE 8080

# Jalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
