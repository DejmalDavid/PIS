import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register-screen',
  templateUrl: './register-screen.component.html',
  styleUrls: ['./register-screen.component.css']
})
export class RegisterScreenComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  registerUser(event) {
    event.preventDefault()
    console.log(event)
  }

}
