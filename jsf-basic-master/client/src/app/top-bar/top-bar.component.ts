import { EventEmitter, Component, OnInit, Output } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';

@Component({
    selector: 'app-top-bar',
    templateUrl: './top-bar.component.html',
    styleUrls: ['./top-bar.component.css']
})

export class TopBarComponent implements OnInit {
    isSuperAdmin = this.auth.getIsSuperAdmin;
    isAdmin = this.auth.getIsAdmin;
    isLogged = this.auth.getIsAdmin;
    username: string;
    
    searchMode: string;
    team: string;
    match: string;

    constructor(private auth: AuthService, private router: Router, private api: ApiService) {
        auth.getLoggedInName.subscribe(name => this.changeName(name));
    }

    ngOnInit() {
        this.searchMode = "Tymy"
    }

    private changeName(name: string): void {
        console.log("change name " + name)
        this.isSuperAdmin = this.auth.getIsSuperAdmin;
        this.isAdmin = this.auth.getIsAdmin;
        this.isLogged = this.auth.getIsLogged;
        this.username = name;
    }

    search(event) {
        if (this.searchMode == "Tymy") {
            this.router.navigate(['search/teams', event.target.value]);
            if (this.router.url.split("/")[1] == "search")
                this.api.reload.emit("teams/" + event.target.value);
        }
        else {
            this.router.navigate(['search/matches', event.target.value]);
            if (this.router.url.split("/")[1] == "search")
                this.api.reload.emit("matches/" + event.target.value);
        }
    }

    logout() {
        this.auth.logout();
    }
    
}