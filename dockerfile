# Etapa 1: Construcción del Frontend
FROM node:18 AS frontend-build
WORKDIR /app
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ .
RUN npm run build -- --configuration production

# Etapa 2: Construcción del Backend
FROM maven:3.9.4-eclipse-temurin-17 AS backend-build
WORKDIR /app
COPY backend/pom.xml .
RUN mvn dependency:go-offline
COPY backend/src ./src

# Crear directorio de recursos estáticos
RUN mkdir -p src/main/resources/static

# Copiar archivos estáticos del frontend
COPY --from=frontend-build /app/dist/frontend/ src/main/resources/static/

# Construir la aplicación
RUN mvn clean package -DskipTests

# Etapa 3: Imagen final con JRE más ligero
FROM eclipse-temurin:17-jre-jammy

# Puerto que expone el contenedor
EXPOSE 10000

# Directorio de trabajo
WORKDIR /app

# Copiamos el JAR construido
COPY --from=backend-build /app/target/*.jar app.jar

# Variables de entorno por defecto
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=10000

# Comando de inicio con soporte para variables de entorno
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-10000} -jar app.jar"]
