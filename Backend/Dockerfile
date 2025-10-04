# ---- Build stage ----
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
# copy file jar đã build
COPY --from=build /app/target/*.jar app.jar

# Render tự dò port; đặt ENV PORT để rõ ràng (không bắt buộc)
ENV PORT=8080

# Spring Boot sẽ đọc server.port=${PORT} (đã set ở application.properties)
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
