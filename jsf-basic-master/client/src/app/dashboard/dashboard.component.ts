import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
    spinner = true;
    content = "none";
    constructor() { }

    ngOnInit() { 
        setTimeout(() => {
            this.spinner = false;
            this.content = "flex";
        }, 1500)
    }
}