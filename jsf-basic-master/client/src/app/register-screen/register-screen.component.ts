import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-screen',
  templateUrl: './register-screen.component.html',
  styleUrls: ['./register-screen.component.css']
})
export class RegisterScreenComponent implements OnInit {

  constructor(private Auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  registerUser(event) {
    event.preventDefault()
    const target = event.target
    const name = target.querySelector('#name').value
    const password = target.querySelector('#password').value
    const email = target.querySelector('#email').value
    const surname = target.querySelector('#surname').value

    this.Auth.registerUser(email, name, surname, password, 1).subscribe(
        data => {
            if (data.Success) {
              this.router.navigate(['login'])
              window.alert("Successfully registered as " + email + ".")
            }
            else {
                console.log(data)
                window.alert("Registrácia zlyhala.")
            }
        }
    )
  }

}
