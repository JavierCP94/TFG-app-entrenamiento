import { NgIf } from "@angular/common";
import { Component } from "@angular/core";

@Component({
  standalone: true,
  selector: "app-header",
  templateUrl: "./header.component.html",
  imports: [NgIf],
})
export class HeaderComponent {
  menuOpen = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
}
