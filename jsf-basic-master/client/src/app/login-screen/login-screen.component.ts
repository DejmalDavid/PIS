import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';

@Component({
    selector: 'app-login-screen',
    templateUrl: './login-screen.component.html',
    styleUrls: ['./login-screen.component.css']
})

export class LoginScreenComponent implements OnInit {
    data;
    
    constructor(private Auth: AuthService, private router: Router, private api: ApiService) { }

    ngOnInit() { }

    loginUser(event) {
        event.preventDefault()
        const target = event.target
        const username = target.querySelector('#username').value
        const password = target.querySelector('#password').value

        this.Auth.loginUser(username, password).subscribe(
            data => {
                if (data.Success) {
                    this.api.getUserInfo(username).subscribe(info => {
                        this.Auth.setRights(info.opravneni)                                           
                        if (this.Auth.getIsLogged) {
                            this.router.navigate(['/'])                    
                            this.Auth.setName(username)
                            console.log("user logged")
                        }
                        else if (this.Auth.getIsAdmin) {
                            this.router.navigate(['admin'])
                            this.Auth.setName(username)
                            console.log("admin logged")
                        }
                        else if (this.Auth.getIsSuperAdmin) {
                            this.router.navigate(['superadmin'])
                            this.Auth.setName(username)
                            console.log("superadmin logged")
                        }
                        else {
                            console.log(data)
                            window.alert("Chyba prihlásenia")
                        }
                    })
                }
                else {
                    console.log(data)
                    window.alert("Nesprávne heslo alebo meno")
                }
            }
        )
    }
}