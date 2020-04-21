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
    this.api.getAllTeam().subscribe(data=>{
      this.timy=data;
    })
    
  }
  zapas = {
    id:"",
    tim_id:0
  };
  hraciTimu;
  hrac1;hrac2;hrac3;hrac4;hrac5;hrac6;hrac7;hrac8;hrac9;hrac10;hrac11;hrac12;hrac13;hrac14;hrac15;
  initSquad(e) {
    console.log(this.zapas);
    var zapa={id:0};
    zapa.id=+this.zapas.id;
    var tym={id:0};
    tym.id=this.zapas.tim_id;
    this.auth.initSquad(zapa,tym).subscribe(data=>{
      console.log(data);
    })
    this.api.getPlayers(this.zapas.tim_id).subscribe(hraci => {
      console.log(hraci);
      this.hraciTimu=hraci;
      })
  }
  newPlayer={
    jmeno:"",
    pozice:0,
    prijmeni:"",
    skill:"",
    vek:0
  }
  sendSquad(e){
    var meno=this.hrac1+'';
    var m=meno.split(" ");
    var sestava={
      jmeno:meno[0],
      pozice:this.hrac1.pozice,
      prijmeni:meno[1],
      vek:this.hrac1.vek
    }
    this.auth.sendSquad(meno).subscribe(hraci => {
      console.log(hraci);
      })
    console.log(this.hrac1);
  }
}
