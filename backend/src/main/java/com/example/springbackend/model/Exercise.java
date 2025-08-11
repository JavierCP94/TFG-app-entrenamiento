package com.example.springbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exercise")
public class Exercise {

    @Id
    private String id;

    private String name;
    private String imageUrl;
    private int sets; // Número de series
    private int repetitions;
    private int restSeconds;

    private String muscleGroup; // "Upper Body", "Lower Body", "Full Body"
    private String level; // "Beginner", "Intermediate", "Advanced"

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getRestSeconds() {
        return restSeconds;
    }

    public void setRestSeconds(int restSeconds) {
        this.restSeconds = restSeconds;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
