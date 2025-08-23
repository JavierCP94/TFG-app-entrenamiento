package com.example.springbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AppProperties appProperties;

    public SecurityConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // Permitir acceso a recursos estáticos y endpoints públicos
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/browser/**",
                                "/assets/**",
                                "/static/**",
                                "/**/*.js",
                                "/**/*.css",
                                "/**/*.ico",
                                "/**/*.png",
                                "/**/*.svg",
                                "/**/*.woff2",
                                "/**/*.woff",
                                "/**/*.ttf",
                                "/**/*.json",
                                "/**/*.jpg",
                                "/**/*.jpeg",
                                "/**/*.gif",
                                "/**/*.html"
                        ).permitAll()
                        // Endpoints públicos de la API
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/health",
                                "/keepalive",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/error"
                        ).permitAll()
                        // Permitir cualquier solicitud OPTIONS (necesario para CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Temporalmente permitir todo el acceso
                        .anyRequest().permitAll()
                )
                // Configuración para manejar el acceso denegado
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write("{\"error\":\"No autorizado. Por favor inicie sesión.\"}");
                        })
                )
                // Deshabilitar el formulario de login por defecto
                .formLogin(form -> form.disable())
                // Deshabilitar la autenticación básica del navegador
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        List<String> allowedOrigins = appProperties.getAllowedOrigins();
        
        // Si se especifica "*" como origen permitido, permitir todos los orígenes
        if (allowedOrigins.contains("*")) {
            configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
            configuration.setAllowCredentials(false); // No se puede usar allowCredentials con "*"
        } else {
            configuration.setAllowedOriginPatterns(allowedOrigins);
            configuration.setAllowCredentials(true);
        }
        
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
