import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getAllMatches() {
    return this.http.get("rest/zapas/list")
    .subscribe(data => {
      console.log(data)
    })
  }

  getAllTeams() {
    return this.http.get("rest/tym/list");
  }
}
