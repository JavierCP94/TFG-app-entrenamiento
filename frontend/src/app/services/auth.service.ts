import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from "../../environments/environment";

export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

export interface AuthResponse {
  token?: string;
  username?: string;
  email?: string;
  firstName?: string;
  lastName?: string;
  message: string;
  success: boolean;
}

export interface User {
  username: string;
  email: string;
  firstName: string;
  lastName: string;
}

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private apiUrl = environment.apiUrl + "/api/auth";
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(private http: HttpClient) {
    const storedUser = localStorage.getItem("currentUser");
    this.currentUserSubject = new BehaviorSubject<User | null>(
      storedUser ? JSON.parse(storedUser) : null
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    if (!environment.production) {
      // Modo desarrollo: simular login exitoso
      const fakeResponse: AuthResponse = {
        success: true,
        token: "dev-token",
        username: loginRequest.username,
        email: "dev@email.com",
        firstName: "Dev",
        lastName: "User",
        message: "Development login bypassed.",
      };
      const user: User = {
        username: fakeResponse.username!,
        email: fakeResponse.email!,
        firstName: fakeResponse.firstName!,
        lastName: fakeResponse.lastName!,
      };
      localStorage.setItem("currentUser", JSON.stringify(user));
      localStorage.setItem("token", fakeResponse.token!);
      this.currentUserSubject.next(user);
      return new Observable<AuthResponse>((observer) => {
        observer.next(fakeResponse);
        observer.complete();
      });
    }
    // Modo producción: login real
    return this.http
      .post<AuthResponse>(`${this.apiUrl}/login`, loginRequest)
      .pipe(
        map((response) => {
          if (response.success && response.token) {
            const user: User = {
              username: response.username!,
              email: response.email!,
              firstName: response.firstName!,
              lastName: response.lastName!,
            };

            localStorage.setItem("currentUser", JSON.stringify(user));
            localStorage.setItem("token", response.token);
            this.currentUserSubject.next(user);
          }
          return response;
        })
      );
  }

  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    if (!environment.production) {
      // Modo desarrollo: simular registro exitoso
      const fakeResponse: AuthResponse = {
        success: true,
        token: "dev-token",
        username: registerRequest.username,
        email: registerRequest.email,
        firstName: registerRequest.firstName,
        lastName: registerRequest.lastName,
        message: "Development register bypassed.",
      };
      const user: User = {
        username: fakeResponse.username!,
        email: fakeResponse.email!,
        firstName: fakeResponse.firstName!,
        lastName: fakeResponse.lastName!,
      };
      localStorage.setItem("currentUser", JSON.stringify(user));
      localStorage.setItem("token", fakeResponse.token!);
      this.currentUserSubject.next(user);
      return new Observable<AuthResponse>((observer) => {
        observer.next(fakeResponse);
        observer.complete();
      });
    }
    // Modo producción: registro real
    return this.http
      .post<AuthResponse>(`${this.apiUrl}/register`, registerRequest)
      .pipe(
        map((response) => {
          if (response.success && response.token) {
            const user: User = {
              username: response.username!,
              email: response.email!,
              firstName: response.firstName!,
              lastName: response.lastName!,
            };

            localStorage.setItem("currentUser", JSON.stringify(user));
            localStorage.setItem("token", response.token);
            this.currentUserSubject.next(user);
          }
          return response;
        })
      );
  }

  logout(): void {
    localStorage.removeItem("currentUser");
    localStorage.removeItem("token");
    this.currentUserSubject.next(null);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem("token");
  }

  getToken(): string | null {
    return localStorage.getItem("token");
  }
}
