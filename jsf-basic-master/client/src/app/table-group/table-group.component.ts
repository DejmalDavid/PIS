import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { GROUPA, GROUPB, GROUPC, GROUPD, GROUPE, GROUPF, GROUPG, GROUPH} from '../teams';


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
  
  
  @Input() source :string;
  ngOnInit() {
    switch (this.source) {
      case "A":
        this.dataSource = new MatTableDataSource(GROUPA);
        break;
      case "B":
        this.dataSource = new MatTableDataSource(GROUPB);
        break;
      case "C":
        this.dataSource = new MatTableDataSource(GROUPC);
        break;
      case "D":
        this.dataSource = new MatTableDataSource(GROUPD);
        break;
      case "E":
        this.dataSource = new MatTableDataSource(GROUPE);
        break;
      case "F":
        this.dataSource = new MatTableDataSource(GROUPF);
        break;
      case "G":
        this.dataSource = new MatTableDataSource(GROUPG);
        break;
    
      default:
        this.dataSource = new MatTableDataSource(GROUPH);
        break;
    }
  }
}
