# Etapa 1: Construcción con Maven y Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Directorio de trabajo en la imagen
WORKDIR /app

# Copiamos pom.xml y descargamos dependencias
COPY backend/pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente y construimos el JAR
COPY backend/src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con JDK
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
