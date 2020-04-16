import { Component, OnInit, Input } from '@angular/core';
import { GROUPA, GROUPB, GROUPC, GROUPD, GROUPE, GROUPF, GROUPG, GROUPH } from '../teams';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { mmatches } from '../matches'
import { ApiService } from '../api.service';



@Component({
    selector: 'app-group-details',
    templateUrl: './group-details.component.html',
    styleUrls: ['./group-details.component.css']
})

export class GroupDetailsComponent implements OnInit {

    displayedColumns: string[] = ['position', 'flag', 'name', 'zapasy', 'skore', 'body'];
    dataSource = new MatTableDataSource();
    displayedColumnsMatches: string[] = ['datum', 'home', 'score', 'away'];
    DSmatches = new MatTableDataSource(mmatches);
    nation: string;
    matchID: string;
    constructor(private route: ActivatedRoute,
        private api: ApiService) { }
    ngOnInit() {
        this.route.paramMap.subscribe(params => {
            switch (params.get('source')) {
                case "A":
                    this.api.getGroup("1").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[1]);
                    })
                    this.api.getGroupMatches("1").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
                    })
                    break;
                case "B":
                    this.api.getGroup("2").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[2]);
                    })
                    break;
                case "C":
                    this.api.getGroup("3").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[3]);
                    })
                    this.api.getGroupMatches("3").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
                    })
                    break;
                case "D":
                    this.api.getGroup("4").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[4]);
                    })
                    break;
                case "E":
                    this.api.getGroup("5").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[5]);
                    })
                    break;
                case "F":
                    this.api.getGroup("6").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[6]);
                    })
                    break;
                case "G":
                    this.api.getGroup("7").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[7]);
                    })
                    break;
                default:
                    this.dataSource = new MatTableDataSource(GROUPH);
                    this.api.getGroup("8").subscribe(data => {
                        var team = (data[0]);
                        this.dataSource = new MatTableDataSource(team[8]);
                    })
                    break;
            }
        })

    }
    onClick(home) {
        this.nation = home;
    }
    onHover(id) {
        this.matchID = id;
    }
}