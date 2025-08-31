package com.example.springbackend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbackend.model.Exercise;
import com.example.springbackend.repository.ExerciseRepository;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = { "https://tfg-app-entrenamiento.onrender.com",
        "http://localhost:4200" }, allowCredentials = "true")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/all")
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @GetMapping
    public List<Exercise> getExercises(
            @RequestParam String muscleGroup,
            @RequestParam String level,
            @RequestParam(defaultValue = "5") int count) {

        if (muscleGroup.equals("Full Body")) {
            if (count == 1) {
                List<Exercise> fullBody = exerciseRepository.findByMuscleGroupAndLevelIgnoreCase("Full Body", level);
                Collections.shuffle(fullBody);
                return fullBody.stream().limit(1).toList();
            } else {
                List<Exercise> upper = new ArrayList<>(
                        exerciseRepository.findByMuscleGroupAndLevelIgnoreCase("Upper Body", level));
                List<Exercise> lower = new ArrayList<>(
                        exerciseRepository.findByMuscleGroupAndLevelIgnoreCase("Lower Body", level));
                Collections.shuffle(upper);
                Collections.shuffle(lower);

                List<Exercise> result = new ArrayList<>();
                int upperIndex = 0;
                int lowerIndex = 0;

                for (int i = 0; i < count; i++) {
                    if (i % 2 == 0 && upperIndex < upper.size()) {
                        result.add(upper.get(upperIndex++));
                    } else if (lowerIndex < lower.size()) {
                        result.add(lower.get(lowerIndex++));
                    }
                }

                return result;
            }
        }

        List<Exercise> filtered = new ArrayList<>(
                exerciseRepository.findByMuscleGroupAndLevelIgnoreCase(muscleGroup, level));
        Collections.shuffle(filtered);
        Set<String> usedNames = new HashSet<>();
        List<Exercise> unique = new ArrayList<>();

        for (Exercise e : filtered) {
            if (usedNames.add(e.getName())) {
                unique.add(e);
                if (unique.size() == count)
                    break;
            }
        }

        return unique;
    }
}
