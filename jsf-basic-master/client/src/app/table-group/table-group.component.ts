import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { GROUPA, GROUPB, GROUPC, GROUPD, GROUPE, GROUPF, GROUPG, GROUPH } from '../teams';
import { ApiService } from '../api.service';
import { ActivatedRoute } from '@angular/router';


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

  constructor(private route: ActivatedRoute, private api: ApiService) {
  }
  nation: string;
  @Input() source: string;
  ngOnInit() {
    this.api.getAllTeams().subscribe(teams => {


      switch (this.source) {
        case "A":
          var team1 = JSON.parse(JSON.stringify(teams[0]));
          var t = team1[1];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[1]);
          break;
        case "B":
          var team1 = JSON.parse(JSON.stringify(teams[1]));
          var t = team1[2];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[2]);
          break;
        case "C":
          var team1 = JSON.parse(JSON.stringify(teams[2]));
          var t = team1[3];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[3]);
          break;
        case "D":
          var team1 = JSON.parse(JSON.stringify(teams[3]));
          var t = team1[4];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[4]);
          break;
        case "E":
          var team1 = JSON.parse(JSON.stringify(teams[4]));
          var t = team1[5];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[5]);
          break;
        case "F":
          var team1 = JSON.parse(JSON.stringify(teams[5]));
          var t = team1[6];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[6]);
          break;
        case "G":
          var team1 = JSON.parse(JSON.stringify(teams[6]));
          var t = team1[7];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[7]);
          break;

        default:
          var team1 = JSON.parse(JSON.stringify(teams[7]));
          var t = team1[8];
          t.sort(this.dynamicSort("body"));
          t.forEach((element, index) => {
            element.position = index + 1;
          });
          this.dataSource = new MatTableDataSource(team1[8]);
          break;
      }
    });

  }
  onClick(home) {
    this.nation = home;
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
