import { Routes } from "@angular/router";
import { LandingComponent } from "./modules/landing/landing.component";
import { WorkoutAppComponent } from "./modules/workout-app/workout-app.component";

export const routes: Routes = [
  { path: "", component: LandingComponent }, // Ruta por defecto (landing)
  { path: "APP", component: WorkoutAppComponent },
  //{ path: "**", redirectTo: "" }, // Redirecciona cualquier ruta no encontrada al landing
];
