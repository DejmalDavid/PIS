import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http'


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  @Output() getLoggedInName: EventEmitter<any> = new EventEmitter();
  
  private isAdmin = false;
  private isLogged = false;
  username: string;

  DATA_Y = {success:true}
  DATA_N = {success:false}

  constructor(private http: HttpClient) { }

  // getUserDetails(username, password) {
  //   return this.http.post('/rest/auth', {
  //     username,
  //     password
  //   })
  // }

  getUserDetails(username, password) {
    if (password == "admin" && username == "admin") {
      console.log("Y")
      this.username = username
      return this.DATA_Y;
    }
    else if (password == "user" && username == "user") {
      console.log("Y")
      this.username = username
      return this.DATA_Y;
    }
    else {
      console.log("N")
      this.username = "Signin"
      return this.DATA_N;
    }
  }

  setIsAdmin(value: boolean) {
      this.isAdmin = value;
      this.getLoggedInName.emit(this.username);
  } 

  get getIsAdmin() {
    return this.isAdmin
  }

  setIsLogged(value: boolean) {
    this.isLogged = value;
    this.getLoggedInName.emit(this.username);
  } 

  get getIsLogged() {
    return this.isLogged
  }

  logout() {
    this.isAdmin = false;
    this.isLogged = false;
    this.username = "Signin"
    this.getLoggedInName.emit(this.username);
  }

}
