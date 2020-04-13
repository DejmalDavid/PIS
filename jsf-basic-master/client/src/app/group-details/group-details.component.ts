import { Component, OnInit, Input } from '@angular/core';
import { GROUPA, GROUPB, GROUPC, GROUPD, GROUPE, GROUPF, GROUPG, GROUPH} from '../teams';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { mmatches} from '../matches'


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
    nation:string;
    constructor(private route: ActivatedRoute) { }
    ngOnInit() { 
        this.route.paramMap.subscribe(params => {
            switch (params.get('source')) {
                case "A":
                    this.dataSource=new MatTableDataSource(GROUPA);
                    break;
                case "B":
                    this.dataSource=new MatTableDataSource(GROUPB);
                    break;
                case "C":
                    this.dataSource=new MatTableDataSource(GROUPC);
                    break;
                default:
                    this.dataSource=new MatTableDataSource(GROUPH);
                    break;
            }
        })
        
    }
    onClick(home){
        this.nation = home;
    }
}