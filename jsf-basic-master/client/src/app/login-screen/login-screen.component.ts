import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login-screen',
    templateUrl: './login-screen.component.html',
    styleUrls: ['./login-screen.component.css']
})

export class LoginScreenComponent implements OnInit {
    data;
    
    constructor(private Auth: AuthService, private router: Router) { }

    ngOnInit() { }

    loginUser(event) {
        event.preventDefault()
        const target = event.target
        const username = target.querySelector('#username').value
        const password = target.querySelector('#password').value

        this.Auth.loginUser(username, password).subscribe(
            data => {
                if (data.Success) {
                    if (username == "admin") {
                        this.router.navigate(['admin'])
                        this.Auth.setIsAdmin(true)
                        console.log("admin logged")
                    }
                    else {
                        this.router.navigate(['/'])
                        this.Auth.setIsLogged(true)
                        console.log("user logged")
                    }
                }
                else {
                    console.log(data)
                    window.alert("Nesprávne heslo alebo meno")
                }
            }
        )

        // if (this.Auth.getUserDetails(username, password).success) {
        //     if (username == "admin") {
        //         this.router.navigate(['admin'])
        //         this.Auth.setIsAdmin(true)
        //         console.log("admin logged")
        //     }
        //     else {
        //         this.router.navigate(['/'])
        //         this.Auth.setIsLogged(true)
        //         console.log("user logged")
        //     }
        // }
        // else {
        //     window.alert("Nesprávne heslo alebo meno")
        // }

        //console.log(username, password)
    }
}