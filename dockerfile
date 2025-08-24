# Etapa 1: Construir el frontend
FROM node:18 AS frontend
WORKDIR /app
COPY frontend/ .
RUN npm install
RUN npm run build -- --configuration=production

# Crear directorio de salida si no existe
RUN mkdir -p /app/output/static

# Mover archivos generados a una ubicación conocida
RUN if [ -d "/app/backend/src/main/resources/static" ]; then \
        mv /app/backend/src/main/resources/static/* /app/output/static/; \
    elif [ -d "/app/dist/frontend" ]; then \
        mv /app/dist/frontend/* /app/output/static/; \
    fi

# Etapa 2: Construir el backend
FROM maven:3.9.4-eclipse-temurin-17 AS backend-build
WORKDIR /app

# Copiar el código del backend
COPY backend/pom.xml .
COPY backend/src ./src

# Crear directorio de recursos estáticos
RUN mkdir -p src/main/resources/static

# Copiar los archivos del frontend construidos
COPY --from=frontend /app/output/static/ src/main/resources/static/

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
