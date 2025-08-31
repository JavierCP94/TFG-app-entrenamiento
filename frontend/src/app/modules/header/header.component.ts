import { NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { RouterModule, Router } from "@angular/router";
import { AuthService, User } from "../../services/auth.service";

@Component({
  standalone: true,
  selector: "app-header",
  templateUrl: "./header.component.html",
  imports: [NgIf, RouterModule],
})
export class HeaderComponent implements OnInit {
  menuOpen = false;
  currentUser: User | null = null;
  isAuthenticated = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
      this.isAuthenticated = !!user;
    });
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  login() {
    this.router.navigate(['/login']);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
