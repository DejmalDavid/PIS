import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface TableMatchesItem {
  datum: string;
  home: string;
  score: string;
  away: string;
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getUserInfo(n:string) {
    return this.http.get<any>("rest/uzivatel/email/"+n);
  }

  getAllMatches() {
    return this.http.get<TableMatchesItem[]>("rest/zapas/list");
  }

  getFavTeams(n:string) {
    return this.http.get<any[]>("rest/uzivatel/oblibene/"+n);
  }

  getAllTeams() {
    return this.http.get("rest/tym/list");
  }

  getGroup(n:string){
    return this.http.get("rest/tym/list/"+n);
  }
  getGroupMatches(n:string){
    return this.http.get("rest/zapas/group/"+n)
  }
  getNationPlayers(n:string){
    return this.http.get("rest/hrac/team/"+n)
  }
  getDetailMatch(n:string){
    return this.http.get("rest/zapas/"+n)
  }
}
