package com.example.springbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://tfg-app-entrenamiento.onrender.com", "http://localhost:4200",
                        "https://workoutappweb.netlify.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin")
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .maxAge(3600); // 1 hora de caché para las preflight requests
    }

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        // Configurar el manejador de recursos estáticos
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);

        // Habilitar el manejo de recursos webjars
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(0);
    }
}
