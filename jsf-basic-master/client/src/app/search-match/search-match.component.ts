import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { SearchMatchDataSource, SearchMatchItem } from './search-match-datasource';
import { ApiService } from '../api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-search-match',
  templateUrl: './search-match.component.html',
  styleUrls: ['./search-match.component.css']
})
export class SearchMatchComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<SearchMatchItem>;
  dataSource: SearchMatchDataSource;

  constructor (private api: ApiService, private route: ActivatedRoute, private router: Router) {
    this.api.reload.subscribe(data => {
      this.router.navigate(['loading', this.router.url, data])
    })
  }

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['datum', 'home', 'score', 'away'];

  ngOnInit() {
    this.dataSource = new SearchMatchDataSource(this.api, this.route);
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }
}
