import { Component, OnInit,ViewChild } from '@angular/core';
import { mmatches} from '../matches';
import { MatTableDataSource } from '@angular/material/table';
import { players } from '../players'
import {MatSort} from '@angular/material/sort';

@Component({
    selector: 'app-team-details',
    templateUrl: './team-details.component.html',
    styleUrls: ['./team-details.component.css']
})

export class TeamDetailsComponent implements OnInit {
    constructor() { }
    displayedColumnsMatches: string[] = ['datum', 'home', 'score', 'away','faza'];
    DSmatches = new MatTableDataSource(mmatches);
    
    displayedColumnsPlayers: string[] = ['number', 'name', 'position','matches','time','goals','assist','yellowcard','redcard'];
    DSplayers = new MatTableDataSource(players);
    
    @ViewChild(MatSort, {static: true}) sort: MatSort;

     
    ngOnInit() {
        this.DSplayers.sort = this.sort;
     }
}