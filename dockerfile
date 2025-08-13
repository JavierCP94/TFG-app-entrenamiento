# Etapa 1: Construcción con Maven y Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom.xml y descargamos dependencias
COPY spring-backend/pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente y construimos el JAR
COPY spring-backend/src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copiamos el JAR generado en la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
