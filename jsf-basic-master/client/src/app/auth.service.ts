import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http'

interface myData {
  Success: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  @Output() getLoggedInName: EventEmitter<any> = new EventEmitter();
  
  private isAdmin = false;
  private isLogged = false;
  private username: string;

  constructor(private http: HttpClient) { }

  loginUser(email, heslo) {
    return this.http.post<myData>('rest/auth/login', {
      email,
      heslo
    })
  }

  registerUser(email, jmeno, prijmeni, heslo, opravneni) {
    return this.http.post<myData>('rest/auth/registration', {
      email,
      jmeno,
      prijmeni,
      heslo,
      opravneni
    })
  }

  setIsAdmin(value: boolean) {
      this.isAdmin = value;
      this.getLoggedInName.emit(this.username);
  } 

  get getIsAdmin() {
    return this.isAdmin;
  }

  setIsLogged(value: boolean) {
    this.isLogged = value;
    this.getLoggedInName.emit(this.username);
  } 

  get getIsLogged() {
    return this.isLogged;
  }

  setName(value: string) {
    this.username = value;
  } 

  get getName() {
    return this.username;
  }

  logout() {
    this.isAdmin = false;
    this.isLogged = false;
    this.username = "Signin";
    this.getLoggedInName.emit(this.username);
  }

}
