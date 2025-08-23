package com.example.springbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String allowedOrigins;

    public List<String> getAllowedOrigins() {
        if (allowedOrigins == null || allowedOrigins.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(allowedOrigins.split("\\s*,\\s*"));
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
