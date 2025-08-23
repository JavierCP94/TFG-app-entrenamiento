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

# Etapa 2: Imagen final con JRE más ligero
FROM eclipse-temurin:17-jre-jammy

# Puerto que expone el contenedor
EXPOSE 10000

# Directorio de trabajo
WORKDIR /app

# Copiamos el JAR construido
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno por defecto
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=10000

# Comando de inicio con soporte para variables de entorno
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-10000} -jar app.jar"]
