package com.example.springbackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbackend.model.Exercise;

@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, String> {

    // Coincidencia sin distinción entre mayúsculas/minúsculas
    @Query("{ 'muscleGroup': { $regex: ?0, $options: 'i' }, 'level': { $regex: ?1, $options: 'i' } }")
    List<Exercise> findByMuscleGroupAndLevelIgnoreCase(String muscleGroup, String level);
}
