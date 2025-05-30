import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { RouterModule, RouterOutlet } from "@angular/router";
import { FooterComponent } from "./modules/footer/footer.component";
import { HeaderComponent } from "./modules/header/header.component";
import { LandingComponent } from "./modules/landing/landing.component";
import { WorkoutAppComponent } from "./modules/workout-app/workout-app.component";

@Component({
  selector: "app-root",
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    HeaderComponent,
    FooterComponent,
    LandingComponent,
    WorkoutAppComponent,
    RouterModule,
  ],
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent {
  title = "Angular Frontend";
}
