import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { GroupDetailsComponent } from './group-details/group-details.component';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { MatchDetailsComponent } from './match-details/match-details.component';
import { MatchListComponent } from './match-list/match-list.component';
import { PlayerDetailsComponent } from './player-details/player-details.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { TeamDetailsComponent } from './team-details/team-details.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MaterialModule } from './material/material.module';
import { TableGroupComponent } from './table-group/table-group.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    GroupDetailsComponent,
    LoginScreenComponent,
    MatchDetailsComponent,
    MatchListComponent,
    PlayerDetailsComponent,
    SearchResultsComponent,
    TeamDetailsComponent,
    TopBarComponent,
    UserProfileComponent,
    TableGroupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    RouterModule.forRoot([
      { path: '', component: DashboardComponent},
      { path: 'group/:source', component: GroupDetailsComponent},
      { path: 'team/:nation', component:TeamDetailsComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
