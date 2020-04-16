import { Component, OnInit,ViewChild } from '@angular/core';
import { mmatches} from '../matches';
import { MatTableDataSource } from '@angular/material/table';
import { players } from '../players'
import {MatSort} from '@angular/material/sort';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { flags } from '../img';

@Component({
    selector: 'app-team-details',
    templateUrl: './team-details.component.html',
    styleUrls: ['./team-details.component.css']
})

export class TeamDetailsComponent implements OnInit {
    constructor(private route: ActivatedRoute,
        private api: ApiService) { }

    displayedColumnsMatches: string[] = ['datum', 'home', 'score', 'away','faza'];
    DSmatches = new MatTableDataSource(mmatches);
    
    displayedColumnsPlayers: string[] = ['number', 'name', 'position','matches','goals','assist'];
    DSplayers = new MatTableDataSource(players);
    
    @ViewChild(MatSort, {static: true}) sort: MatSort;

    imgSrc:string;
    ngOnInit() {
        this.DSplayers.sort = this.sort;

        this.route.paramMap.subscribe(params => {
            
            var id = params.get('nation');
            this.imgSrc = flags[(+id)-1];
            this.api.getNationPlayers(id).subscribe((data: [])=>{
                this.DSplayers=new MatTableDataSource(data);
            })
        });
     }
}