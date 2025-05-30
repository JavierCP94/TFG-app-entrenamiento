package com.example.springbackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springbackend.model.Exercise;

@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> findByMuscleGroupAndLevel(String muscleGroup, String level);
}
