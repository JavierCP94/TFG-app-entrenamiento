# Etapa 1: Construcción con Maven y Java 17
FROM maven:3.8.6-openjdk-17 AS build

# Directorio de trabajo en la imagen
WORKDIR /app

# Copiar el pom.xml y descargar dependencias para cachear
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera solo con Java para ejecutar
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el .jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Render usará una variable PORT, la exponemos
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java","-jar","app.jar"]
