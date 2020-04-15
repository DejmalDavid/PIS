import { Component, OnInit } from '@angular/core';
import { mmatches } from '../matches';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { players } from '../players';

@Component({
    selector: 'app-match-details',
    templateUrl: './match-details.component.html',
    styleUrls: ['./match-details.component.css']
})

export class MatchDetailsComponent implements OnInit {
    
    score: string;
    displayedColumnsSquadHome: string[] = ['numberHome', 'playerHome'];
    displayedColumnsSquadAway: string[] = ['playerAway', 'numberAway'];
    
    dataSourceSquadHome = new MatTableDataSource(players);
    dataSourceSquadAway = new MatTableDataSource(players);
    goalsByPlayer:Int8Array;

    constructor(private route: ActivatedRoute) { 
    }
    ngOnInit() {

        this.route.paramMap.subscribe(params => {
            mmatches.forEach(element => {
                if (element.id == +params.get('matchID')) {
                    this.score = element.score;
                }
            });
        });

    }
    arrayOne(n: string): any[] {
        var arr = new Array(+n);
        
        return arr;
      }



}