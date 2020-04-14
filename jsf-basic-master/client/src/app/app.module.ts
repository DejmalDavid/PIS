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
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { TableMatchComponent } from './table-match/table-match.component';
import {MatCardModule} from '@angular/material/card';
import { RegisterScreenComponent } from './register-screen/register-screen.component';


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
    TableGroupComponent,
    TableMatchComponent,
    RegisterScreenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatInputModule,
    MatButtonModule,
    RouterModule.forRoot([
      { path: '', component: DashboardComponent},
      { path: 'group/:source', component: GroupDetailsComponent},
      { path: 'team/:nation', component:TeamDetailsComponent},
      { path: 'login', component:LoginScreenComponent},
      { path: 'register', component:RegisterScreenComponent}
    ]),
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
