package com.example.springbackend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
                return exerciseRepository.findByMuscleGroupAndLevel("Full Body", level);
            } else {
                List<Exercise> upper = exerciseRepository.findByMuscleGroupAndLevel("Upper Body", level);
                List<Exercise> lower = exerciseRepository.findByMuscleGroupAndLevel("Lower Body", level);
                List<Exercise> mixed = new ArrayList<>();
                mixed.addAll(upper);
                mixed.addAll(lower);
                Collections.shuffle(mixed);
                return mixed.stream().limit(count).collect(Collectors.toList());
            }
        }

        return exerciseRepository.findByMuscleGroupAndLevel(muscleGroup, level)
                .stream().limit(count)
                .collect(Collectors.toList());
    }

}
