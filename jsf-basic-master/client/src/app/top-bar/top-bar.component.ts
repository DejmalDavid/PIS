import { ChangeDetectorRef, ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
    selector: 'app-top-bar',
    templateUrl: './top-bar.component.html',
    styleUrls: ['./top-bar.component.css']
})

export class TopBarComponent implements OnInit {
    isAdmin = this.auth.getIsAdmin;
    isLogged = this.auth.getIsAdmin;
    username: string;

    constructor(private auth: AuthService) {
        auth.getLoggedInName.subscribe(name => this.changeName(name));
    }

    ngOnInit() { 
    }

    private changeName(name: string): void {
        console.log("change name " + name)
        this.isAdmin = this.auth.getIsAdmin;
        this.isLogged = this.auth.getIsLogged;
        this.username = name;
        console.log(this.isAdmin, this.isLogged)
    }

    logout() {
        this.auth.logout();
    }
    
}