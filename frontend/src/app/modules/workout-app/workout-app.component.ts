import { NgFor, NgIf } from "@angular/common";
import { Component } from "@angular/core";
import {
  Exercise as ExerciseModel,
  ExerciseService,
} from "../../exercise.service";

@Component({
  selector: "app-workout-app",
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: "./workout-app.component.html",
  styleUrl: "./workout-app.component.scss",
})
export class WorkoutAppComponent {
  activeMenu: number | null = null;

  selectedMuscleGroup = "";
  selectedLevel = "";
  selectedNumberOfExercises = 0;

  exercises: ExerciseModel[] = [];

  buttons = [
    {
      id: 1,
      label: "Muscle Group",
      options: ["Upper Body", "Lower Body", "Full Body"],
    },
    {
      id: 2,
      label: "Level",
      options: ["Beginner", "Intermediate", "Advanced"],
    },
    {
      id: 3,
      label: "Number of exercise",
      options: ["1", "2", "3", "4", "5"],
    },
  ];

  constructor(private exerciseService: ExerciseService) {}

  toggleMenu(id: number) {
    this.activeMenu = this.activeMenu === id ? null : id;
  }

  selectOption(buttonId: number, option: string) {
    if (buttonId === 1) this.selectedMuscleGroup = option;
    if (buttonId === 2) this.selectedLevel = option;
    if (buttonId === 3) this.selectedNumberOfExercises = +option;

    this.activeMenu = null;

    if (
      this.selectedMuscleGroup &&
      this.selectedLevel &&
      this.selectedNumberOfExercises
    ) {
      this.fetchExercises();
    }
  }

  fetchExercises() {
    this.exerciseService
      .getExercises(
        this.selectedMuscleGroup,
        this.selectedLevel,
        this.selectedNumberOfExercises
      )
      .subscribe({
        next: (data) => {
          this.exercises = data;
        },
        error: (err) => {
          console.error("Error fetching exercises:", err);
        },
      });
  }
}
