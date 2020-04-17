import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ApiService } from '../api.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  displayedColumns: string[] = ['flag', 'name', 'city', 'group', 'couch'];
  dataSource = new MatTableDataSource();

  constructor(private api: ApiService, private auth: AuthService) { }

  ngOnInit(): void {
    this.api.getFavTeams(this.auth.getName).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    })
  }

} 