import { Component, OnInit,ViewChild } from '@angular/core';
import { mmatches} from '../matches';
import { MatTableDataSource } from '@angular/material/table';
import { players } from '../players'
import {MatSort} from '@angular/material/sort';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { flags } from '../img';
import { AuthService } from '../auth.service';

@Component({
    selector: 'app-team-details',
    templateUrl: './team-details.component.html',
    styleUrls: ['./team-details.component.css']
})

export class TeamDetailsComponent implements OnInit {
    constructor(private route: ActivatedRoute,
        private api: ApiService, private auth: AuthService) { }

    isLogged: boolean;
    teamId: number;
    inFavs = false;
    matchID:string;

    displayedColumnsMatches: string[] = ['datum', 'home', 'score', 'away','faza'];
    DSmatches = new MatTableDataSource();
    
    displayedColumnsPlayers: string[] = ['number', 'name', 'vek','position','matches','goals','assist'];
    DSplayers = new MatTableDataSource();
    
    @ViewChild(MatSort, {static: true}) sort: MatSort;

    imgSrc:string;
    ngOnInit() {
        
        this.isLogged = this.auth.getIsLogged
        this.route.paramMap.subscribe(params => {
            this.teamId = parseInt(params.get('nation'));
            this.imgSrc = flags[(this.teamId)-1];
            this.api.getNationPlayers(this.teamId.toString()).subscribe((data: [])=>{
                this.DSplayers=new MatTableDataSource(data);
                this.DSplayers.sort = this.sort;
            })
            this.api.getMatchesByTeam(+this.teamId).subscribe((data: [])=>{
                this.DSmatches=new MatTableDataSource(data);
            })

        });

        this.api.getFavTeams(this.auth.getName).subscribe(data => {
            for (let i = 0; i<data.length; i++) {
                if (data[i].id == this.teamId) {
                    this.inFavs = true;
                }
            }
        })
    }

    addFav() {
        this.api.getUserInfo(this.auth.getName).subscribe(data => {
            this.api.addFavTeam(data.id, this.teamId).subscribe(() => {
                console.log("added" + this.teamId)
            });
        })
        this.inFavs = true;
    }

    deleteFav() {
        this.api.getUserInfo(this.auth.getName).subscribe(data => {
            this.api.getFavAll().subscribe(info => {
                if (info.length > 0) {
                    for (let i = 0; i<info.length; i++) {
                        if (info[i].uzivatel.id == data.id && info[i].tym.id == this.teamId) {
                            this.api.deleteFav(info[i].id).subscribe(() => {
                                console.log("fav removed")
                            })
                            this.inFavs = false;
                        }
                    }
                }
            })
        });
    }

    onHover(id) {
        this.matchID = id;
    }
}