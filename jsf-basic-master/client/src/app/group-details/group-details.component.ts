import { Component, OnInit, Input } from '@angular/core';
import { GROUPA, GROUPB, GROUPC, GROUPD, GROUPE, GROUPF, GROUPG, GROUPH } from '../teams';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { mmatches } from '../matches'
import { ApiService } from '../api.service';
import { flagsIcon } from '../img';



@Component({
    selector: 'app-group-details',
    templateUrl: './group-details.component.html',
    styleUrls: ['./group-details.component.css']
})

export class GroupDetailsComponent implements OnInit {

    displayedColumns: string[] = ['position', 'flag', 'name', 'zapasy', 'skore', 'body'];
    dataSource = new MatTableDataSource();
    displayedColumnsMatches: string[] = ['datum', 'home', 'score', 'away'];
    DSmatches = new MatTableDataSource();
    nation: string;
    icon = flagsIcon;
    matchID: string;
    constructor(private route: ActivatedRoute,
        private api: ApiService) { }
    ngOnInit() {

        this.route.paramMap.subscribe(params => {
            switch (params.get('source')) {
                case "A":
                    this.api.getGroup("1").subscribe(data => {
                        var team = (data[0]);
                        var t = team[1];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
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
                        var t = team[2];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
                        this.dataSource = new MatTableDataSource(team[2]);
                    })
                    this.api.getGroupMatches("2").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
                    })
                    break;
                case "C":
                    this.api.getGroup("3").subscribe(data => {
                        var team = (data[0]);
                        var t = team[3];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
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
                        var t = team[4];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
                        this.dataSource = new MatTableDataSource(team[4]);
                    })
                    this.api.getGroupMatches("4").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
                    })
                    break;
                case "E":
                    this.api.getGroup("5").subscribe(data => {
                        var team = (data[0]);
                        var t = team[5];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
                        this.dataSource = new MatTableDataSource(team[5]);
                    })
                    this.api.getGroupMatches("5").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
                    })
                    break;
                case "F":
                    this.api.getGroup("6").subscribe(data => {
                        var team = (data[0]);
                        var t = team[6];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
                        this.dataSource = new MatTableDataSource(team[6]);
                    })
                    this.api.getGroupMatches("6").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
                    })
                    break;
                case "G":
                    this.api.getGroup("7").subscribe(data => {
                        var team = (data[0]);
                        var t = team[7];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
                        this.dataSource = new MatTableDataSource(team[7]);
                    })
                    this.api.getGroupMatches("7").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
                    })
                    break;
                default:
                    this.api.getGroup("8").subscribe(data => {
                        var team = (data[0]);
                        var t = team[8];
                        console.log(t);
                        t.sort(this.dynamicSort("body"));
                        t.forEach((element, index) => {
                            element.position = index + 1;
                        });
                        this.dataSource = new MatTableDataSource(team[8]);
                    })
                    this.api.getGroupMatches("8").subscribe(data => {
                        var team = JSON.parse(JSON.stringify(data));
                        this.DSmatches = new MatTableDataSource(team);
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
    setIcon(id) {
        alert(id);
        return flagsIcon[id]
    }
    dynamicSort(property) {
        var sortOrder = 1;
        if (property[0] === "-") {
            sortOrder = -1;
            property = property.substr(1);
        }
        return function (a, b) {
            /* next line works with strings and numbers, 
             * and you may want to customize it to your needs
             */
            var result = (a[property] > b[property]) ? -1 : (a[property] < b[property]) ? 1 : 0;
            return result * sortOrder;
        }
    }

}