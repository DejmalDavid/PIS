import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { mmatches8, mmatches4, mmatches2 } from '../matches';

@Component({
  selector: 'app-table-match',
  templateUrl: './table-match.component.html',
  styleUrls: ['./table-match.component.css']
})
export class TableMatchComponent implements OnInit {

  displayedColumns: string[] = ['home', 'score', 'away'];
  dataSource = new MatTableDataSource();

  constructor() { }

  @Input() source :string;
  ngOnInit(): void {
    switch (this.source) {
      case "8":
        this.dataSource = new MatTableDataSource(mmatches8);
        break;
      case "4":
        this.dataSource = new MatTableDataSource(mmatches4);
        break;
      case "2":
        this.dataSource = new MatTableDataSource(mmatches2);
        break;
    }
  }

}
