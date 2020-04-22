import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-new-squad',
  templateUrl: './new-squad.component.html',
  styleUrls: ['./new-squad.component.css']
})
export class NewSquadComponent implements OnInit {

  constructor(private route: ActivatedRoute, private api: ApiService, private auth: AuthService) { }
  timy;
  ngOnInit(): void {
    this.api.getAllTeam().subscribe(data => {
      this.timy = data;
    })

  }
  zapas = {
    id: "",
    tim_id: 0
  };
  hraciTimu;
  hrac1; hrac2; hrac3; hrac4; hrac5; hrac6; hrac7; hrac8; hrac9; hrac10; hrac11; hrac12; hrac13; hrac14; hrac15;
  initSquad(e) {
    console.log(this.zapas);
    var zapa = { id: 0 };
    zapa.id = +this.zapas.id;
    var tym = { id: 0 };
    tym.id = this.zapas.tim_id;
    this.auth.initSquad(zapa, tym).subscribe(data => {
      console.log(data);
    })
    this.api.getPlayers(this.zapas.tim_id).subscribe(hraci => {
      console.log(hraci);
      this.hraciTimu = hraci;
    })
  }
  newPlayer = {
    jmeno: "",
    pozice: 0,
    prijmeni: "",
    skill: "",
    vek: 0
  }
  sendSquad(e) {
    var sestavaid = 0;
    this.api.getSquad(this.zapas.tim_id, +this.zapas.id).subscribe(data => {
      console.log(data);
      sestavaid = data[0]['id_sestava'];
      var sestava1 = {
        id: sestavaid,
        zapa: { id: this.zapas.id },
        tym: { id: this.zapas.tim_id }
      }
      var sestava2 = {
        id: sestavaid,
        zapa: { id: this.zapas.id },
        tym: { id: this.zapas.tim_id }
      }


      if (typeof this.hrac1 === 'undefined') { }
      else {
        var hracc1 = { id: this.hrac1.id }
        this.auth.sendSquad(sestava1, sestava2, hracc1, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac2 === 'undefined') { }
      else {
        var hracc2 = { id: this.hrac2.id }
        this.auth.sendSquad(sestava1, sestava2, hracc2, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac3 === 'undefined') { }
      else {
        var hracc3 = { id: this.hrac3.id }
        this.auth.sendSquad(sestava1, sestava2, hracc3, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac4 === 'undefined') { }
      else {
        var hracc4 = { id: this.hrac4.id }
        this.auth.sendSquad(sestava1, sestava2, hracc4, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac5 === 'undefined') { }
      else {
        var hracc5 = { id: this.hrac5.id }
        this.auth.sendSquad(sestava1, sestava2, hracc5, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac6 === 'undefined') { }
      else {
        var hracc6 = { id: this.hrac6.id }

        this.auth.sendSquad(sestava1, sestava2, hracc6, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac7 === 'undefined') { }
      else {
        var hracc7 = { id: this.hrac7.id }
        this.auth.sendSquad(sestava1, sestava2, hracc7, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac8 === 'undefined') { }
      else {
        var hracc8 = { id: this.hrac8.id }
        this.auth.sendSquad(sestava1, sestava2, hracc8, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac9 === 'undefined') { }
      else {
        var hracc9 = { id: this.hrac9.id }
        this.auth.sendSquad(sestava1, sestava2, hracc9, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }

      if (typeof this.hrac10 === 'undefined') { }
      else {
        var hracc10 = { id: this.hrac10.id }
        this.auth.sendSquad(sestava1, sestava2, hracc10, sestavaid).subscribe(hraci => {
          console.log(hraci);
        })
      }
    })



  }
}
