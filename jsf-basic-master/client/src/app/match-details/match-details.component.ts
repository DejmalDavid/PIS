import { Component, OnInit } from '@angular/core';
import { mmatches } from '../matches';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { players } from '../players';
import { ApiService } from '../api.service';
import { flags } from '../img';

@Component({
    selector: 'app-match-details',
    templateUrl: './match-details.component.html',
    styleUrls: ['./match-details.component.css']
})

export class MatchDetailsComponent implements OnInit {
    
    score: string;
    displayedColumnsSquadHome: string[] = ['numberHome', 'playerHome'];
    displayedColumnsSquadAway: string[] = ['playerAway', 'numberAway'];
    
    dataSourceSquadHome = new MatTableDataSource();
    dataSourceSquadAway = new MatTableDataSource(players);
    goalsByPlayer:Int8Array;
    domaciTim:string;
    hostiaTim:string;

    constructor(private route: ActivatedRoute, private api: ApiService) { 
    }
    ngOnInit() {

        /*this.route.paramMap.subscribe(params => {
            mmatches.forEach(element => {
                if (element.id == +params.get('matchID')) {
                    this.score = element.score;
                }
            });
        });*/
        this.route.paramMap.subscribe(params => {
            var id = params.get('matchID');
            this.api.getDetailMatch(id).subscribe(data =>{
                var datum = (data['datum']);
                var staidion = data['stadion'];
                var hoste = data['hoste'];
                var domaci = data['domaci'];
                this.dataSourceSquadHome = domaci['sestava'];
                this.dataSourceSquadAway = hoste['sestava'];
                this.domaciTim = domaci['tym'];
                this.hostiaTim = hoste['tym'];
                this.score = data['domaci_goly']+":"+data['hoste_goly']
                

            })
        })

    }
    arrayOne(n: string): any[] {
        var arr = new Array(+n);
        
        return arr;
      }



}