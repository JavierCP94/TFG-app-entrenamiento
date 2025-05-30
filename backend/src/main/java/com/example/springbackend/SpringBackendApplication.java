
package com.example.springbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackendApplication.class, args);
    }

    /*
     * @Bean
     * CommandLineRunner init(ExerciseRepository repository) {
     * return args -> {
     * if (repository.count() == 0) {
     * Exercise pushups = new Exercise();
     * pushups.setName("Push Ups");
     * pushups.setImageUrl("https://example.com/images/pushups.png");
     * pushups.setSeries(3);
     * pushups.setRepetitions(15);
     * pushups.setRestSeconds(60);
     * pushups.setMuscleGroup("Upper Body");
     * pushups.setLevel("Beginner");
     * 
     * repository.save(pushups);
     * System.out.println("✔ Inserted example exercise into MongoDB");
     * }
     * };
     * }
     */
}
