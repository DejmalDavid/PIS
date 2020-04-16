import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { GROUPA, GROUPB, GROUPC, GROUPD, GROUPE, GROUPF, GROUPG, GROUPH} from '../teams';
import { ApiService } from '../api.service';


export interface PeriodicElement {
  name: string;
  position: number;
  zapasy: number;
  body: string;
}



@Component({
  selector: 'app-table-group',
  templateUrl: './table-group.component.html',
  styleUrls: ['./table-group.component.css']
})
export class TableGroupComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'zapasy', 'body'];
  dataSource = new MatTableDataSource();
  
  constructor(private api: ApiService) {  
  }
  @Input() source :string;
  ngOnInit() {
    this.api.getAllTeams().subscribe(teams => { 
     
      
      switch (this.source) {
        case "A":
          var team1 = JSON.parse(JSON.stringify(teams[0]));
          this.dataSource = new MatTableDataSource(team1[1]);
          break;
        case "B":
          var team1 = JSON.parse(JSON.stringify(teams[1]));
          this.dataSource = new MatTableDataSource(team1[2]);
          break;
        case "C":
          var team1 = JSON.parse(JSON.stringify(teams[2]));
          this.dataSource = new MatTableDataSource(team1[3]);
          break;
        case "D":
          var team1 = JSON.parse(JSON.stringify(teams[3]));
          this.dataSource = new MatTableDataSource(team1[4]);
          break;
        case "E":
          var team1 = JSON.parse(JSON.stringify(teams[4]));
          this.dataSource = new MatTableDataSource(team1[5]);
          break;
        case "F":
          var team1 = JSON.parse(JSON.stringify(teams[5]));
          this.dataSource = new MatTableDataSource(team1[6]);
          break;
        case "G":
          var team1 = JSON.parse(JSON.stringify(teams[6]));
          this.dataSource = new MatTableDataSource(team1[7]);
          break;
      
        default:
          var team1 = JSON.parse(JSON.stringify(teams[0]));
          this.dataSource = new MatTableDataSource(team1[1]);
          break;
      }
  });
    
  }
}
