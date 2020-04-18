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
  
  private isAdmin = localStorage.getItem('adminIn') || false
  private isLogged = localStorage.getItem('loggedIn') || false
  private username = localStorage.getItem('username') || 'Signin'

  constructor(private http: HttpClient) { }

  refreshState() {
    this.setIsAdmin(JSON.parse(localStorage.getItem('adminIn') || 'false'))
    this.setIsLogged(JSON.parse(localStorage.getItem('loggedIn') || 'false'))
    this.setName(localStorage.getItem('username') || 'Signin')
  }

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
      localStorage.setItem('adminIn', value.toString());
      this.getLoggedInName.emit(this.username);
  } 

  get getIsAdmin() {
    return JSON.parse(localStorage.getItem('adminIn') || this.isAdmin.toString());
  }

  setIsLogged(value: boolean) {
    this.isLogged = value;
    localStorage.setItem('loggedIn', value.toString());
    this.getLoggedInName.emit(this.username);
  } 

  get getIsLogged() {
    return JSON.parse(localStorage.getItem('loggedIn') || this.isLogged.toString());
  }

  setName(value: string) {
    this.username = value;
    localStorage.setItem('username', value);
  } 

  get getName() {
    return localStorage.getItem('username') || this.username.toString();
  }

  logout() {
    this.isAdmin = false;
    this.isLogged = false;
    this.username = "Signin";
    localStorage.setItem('adminIn', 'false');
    localStorage.setItem('loggedIn', 'false');
    localStorage.setItem('username', 'Signin');
    this.getLoggedInName.emit(this.username);
  }

}
