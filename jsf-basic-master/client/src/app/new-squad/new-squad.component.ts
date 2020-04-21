import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-squad',
  templateUrl: './new-squad.component.html',
  styleUrls: ['./new-squad.component.css']
})
export class NewSquadComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  zapas = {
    id:0,
    tim_id:0
  };
  chreateSquad(e) {
    console.log(this.zapas);
  }
}
