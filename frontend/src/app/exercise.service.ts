import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../environments/environment";

export interface Exercise {
  id: string;
  name: string;
  imageUrl: string;
  sets: number;
  repetitions: number;
  restSeconds: number;
  muscleGroup: string;
  level: string;
}

@Injectable({
  providedIn: "root",
})
export class ExerciseService {
  private baseUrl = environment.apiUrl + "/api/exercises";

  constructor(private http: HttpClient) {}

  getExercises(
    muscleGroup: string,
    level: string,
    count: number
  ): Observable<Exercise[]> {
    const params = new HttpParams()
      .set("muscleGroup", muscleGroup)
      .set("level", level)
      .set("count", count.toString());

    // Realiza una solicitud GET al endpoint del backend
    return this.http.get<Exercise[]>(this.baseUrl, { params });
  }
}
