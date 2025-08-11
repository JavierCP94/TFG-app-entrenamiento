import { Routes } from "@angular/router";
import { LandingComponent } from "./modules/landing/landing.component";
import { WorkoutAppComponent } from "./modules/workout-app/workout-app.component";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { AuthGuard } from "./guards/auth.guard";

export const routes: Routes = [
  { path: "", component: LandingComponent }, // Ruta por defecto (landing)
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "APP", component: WorkoutAppComponent, canActivate: [AuthGuard] },
  { path: "dashboard", component: WorkoutAppComponent, canActivate: [AuthGuard] },
  { path: "**", redirectTo: "" }, // Redirecciona cualquier ruta no encontrada al landing
];
