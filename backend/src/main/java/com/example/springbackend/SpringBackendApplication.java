
package com.example.springbackend;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import com.example.springbackend.model.Exercise;
import com.example.springbackend.repository.ExerciseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.springbackend.config")
public class SpringBackendApplication {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            // Solo cargar datos si la base de datos está vacía
            if (exerciseRepository.count() == 0) {
                loadExercisesFromJson();
            } else {
                System.out.println("✔ Exercises already loaded in MongoDB (" + exerciseRepository.count() + " exercises)");
            }
        };
    }

    private void loadExercisesFromJson() {
        try {
            // Cargar el archivo JSON desde el classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("exercises_app.json");
            
            if (inputStream == null) {
                System.err.println("❌ Could not find exercises_app.json in classpath");
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            List<Exercise> exercises = objectMapper.readValue(inputStream, new TypeReference<List<Exercise>>() {});
            
            exerciseRepository.saveAll(exercises);
            System.out.println("✔ Successfully loaded " + exercises.size() + " exercises from JSON into MongoDB");
            
        } catch (IOException e) {
            System.err.println("❌ Error loading exercises from JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
