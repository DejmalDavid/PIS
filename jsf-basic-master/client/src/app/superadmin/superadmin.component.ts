import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ApiService } from '../api.service';

export interface rights {
  id: number;
  datum_reg: number;
  email: string;
  heslo: string;
  jmeno: string;
  prijmeni: string;
  opravneni: number;
}

@Component({
  selector: 'app-superadmin',
  templateUrl: './superadmin.component.html',
  styleUrls: ['./superadmin.component.css']
})
export class SuperadminComponent implements OnInit {
  displayedColumns: string[] = ['email', 'jmeno', 'prijmeni', 'id', 'opravneni'];
  dataSource = new MatTableDataSource();
  rightsArray: rights[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.api.getAllUsers().subscribe((data : Array<any>) => {
      for (let i = 0; i<data.length; i++) {
        this.rightsArray[i] = data[i]
      }
      this.dataSource = new MatTableDataSource(data);
      console.log(this.rightsArray)
    })
  }

  changeRights(i) {
    this.api.updateUser(
      this.rightsArray[i].id,
      this.rightsArray[i].datum_reg,
      this.rightsArray[i].email,
      this.rightsArray[i].heslo,
      this.rightsArray[i].jmeno,
      this.rightsArray[i].opravneni,
      this.rightsArray[i].prijmeni,        
      ).subscribe(() => {
        console.log("update user rights")
      })
  }

  deleteUser(id) {
    this.api.deleteUser(id).subscribe(() => {
      window.alert("Užívateľ odstránený.")
      window.location.reload();
    })
  }
}
