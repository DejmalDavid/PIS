import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { mmatches8, mmatches4, mmatches2, mmatches1 } from '../matches';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-table-match',
  templateUrl: './table-match.component.html',
  styleUrls: ['./table-match.component.css']
})
export class TableMatchComponent implements OnInit {

  displayedColumns: string[] = ['home', 'score', 'away'];
  dataSource = new MatTableDataSource();

  constructor(private api: ApiService) { }
  matchID; string;
  @Input() source: string;
  faza: string;
  ngOnInit(): void {
    this.api.getVyradovaciaFaza().subscribe(matches => {

      switch (this.source) {
        case "8":
          this.dataSource = new MatTableDataSource(mmatches8);
          this.faza = "Osemfinále";
          break;
        case "4":
          this.dataSource = new MatTableDataSource(matches[1][11]);
          this.faza = "Štvrťfinále";
          break;
        case "2":
          this.dataSource = new MatTableDataSource(matches[2][12]);
          this.faza = "Semifinále";
          break;
        case "1":
          this.dataSource = new MatTableDataSource(matches[4][14]);
          this.faza = "Finále";
          break;
      }
    });

  }
  onHover(id) {
    this.matchID = id;
  }
}
