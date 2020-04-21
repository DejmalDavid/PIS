import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of as observableOf, merge } from 'rxjs';

export interface TableMatchesItem {
  datum: string;
  home: string;
  score: string;
  away: string;
  id: number;
}

export interface SearchTeamItem {
  name: string;
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  @Output() reload: EventEmitter<any> = new EventEmitter();

  constructor(private http: HttpClient) { }

  addFavTeam(Uzivatelid:number, Tymid:number) {
    return this.http.post<any>('rest/oblibenetymy', {
      Uzivatelid,
      Tymid
    })
  }

  getSearch(n:string) {
    return this.http.get<any>("rest/search/"+n);
  }

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
  getAllTeam(){
    return this.http.get("rest/tym/all");
  }
  getPlayers(n:number){
    return this.http.get("rest/hrac/team/"+n);
  }
  getVyradovaciaFaza(){
    return this.http.get("rest/zapas/faze");
  }
}
