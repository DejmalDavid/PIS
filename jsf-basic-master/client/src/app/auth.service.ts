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
  
  private isSuperAdmin = false
  private isAdmin = false
  private isLogged = false
  private username = localStorage.getItem('username') || 'Signin'

  constructor(private http: HttpClient) { }

  refreshState() {
    this.setRights(parseInt(localStorage.getItem('valid') || '0'))
    this.setName(localStorage.getItem('username') || 'Signin')
    console.log(this.isSuperAdmin, this.isAdmin, this.isLogged, this.username)
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

 /* deleteMatch(id) {
    return this.http.post<myData>('rest/auth/registration', {
      id
    })
  }*/

  sendGoal(gol_cas,gol_typ,polovina_zapasu,zapa,hrac1,hrac2) {
    return this.http.post('rest/gol', {
      gol_cas,
      gol_typ,
      polovina_zapasu,
      zapa,
      hrac1,
      hrac2
    })
  }
  deleteGoal(id) {
    return this.http.delete('rest/gol/'+id, {
    })
  }
  sendSub(id,cas,hrac_id_in,hrac_id_out,zapa) {
    return this.http.post('rest/stridani', {
      id,
      cas,
      hrac_id_in,
      hrac_id_out,
      zapa
    })
  }
  deleteSub(id) {
    return this.http.delete('rest/stridani/'+id, {
    })
  }
  initSquad(zapa,tym,hostujuci){
    return this.http.post('rest/sestava/', {
      hostujuci,
      zapa,
      tym
    })
    
  }
  sendSquad(sestava1,sestava2,hrac,id){
    return this.http.post('rest/sestavahrac',{
      sestava1,
      sestava2,
      hrac
   
    },{ responseType: 'text' as 'json' })
  }
  updateMatch(stadion,pocet_divaku,rozhodci,id:number){
    return this.http.post('rest/zapas/'+id,{
        stadion,
        pocet_divaku,
        rozhodci
    })
  }
  setRights(value: number) {
      if (value == 1) {
        localStorage.setItem('valid', value.toString());
        this.isLogged = true;
      }
      else if (value == 2) {
        localStorage.setItem('valid', value.toString());
        this.isAdmin = true;
      }
      else if (value == 3) {
        localStorage.setItem('valid', value.toString());
        this.isSuperAdmin = true;
      }
      else {
        this.logout()
      }
  }

  get getIsSuperAdmin() {
    return this.isSuperAdmin;
  }

  setIsAdmin(value: boolean) {
      this.isAdmin = value;
  } 

  get getIsAdmin() {
    return this.isAdmin;
  }

  setIsLogged(value: boolean) {
    this.isLogged = value;
  } 

  get getIsLogged() {
    return this.isLogged;
  }

  setName(value: string) {
    this.username = value;
    localStorage.setItem('username', value);
    this.getLoggedInName.emit(this.username);
  } 

  get getName() {
    return localStorage.getItem('username') || this.username.toString();
  }

  logout() {
    this.isSuperAdmin = false;
    this.isAdmin = false;
    this.isLogged = false;
    this.username = "Signin";
    localStorage.setItem('username', 'Signin');
    localStorage.setItem('valid', '0');
    this.getLoggedInName.emit(this.username);
  }
}
