import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'; 

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
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { TableMatchComponent } from './table-match/table-match.component';
import {MatCardModule} from '@angular/material/card';
import { RegisterScreenComponent } from './register-screen/register-screen.component';
import { TableMatchesComponent } from './table-matches/table-matches.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './auth.guard';
import { AuthService } from './auth.service';
import { ApiService } from './api.service';
import { FavoriteComponent } from './favorite/favorite.component';
import { LogGuard } from './log.guard';
import { SuperadminComponent } from './superadmin/superadmin.component';
import { SuperGuard } from './super.guard';
import { LoginGuard } from './login.guard';


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
    RegisterScreenComponent,
    TableMatchesComponent,
    AdminComponent,
    FavoriteComponent,
    SuperadminComponent
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
      { path: 'login', component:LoginScreenComponent, canActivate: [LoginGuard]},
      { path: 'match/:matchID', component:MatchDetailsComponent},
      { path: 'register', component:RegisterScreenComponent},
      { path: 'matches', component:TableMatchesComponent},
      { path: 'admin', component:AdminComponent, canActivate: [AuthGuard]},
      { path: 'superadmin', component:SuperadminComponent, canActivate: [SuperGuard]},
      { path: 'favorite', component:FavoriteComponent, canActivate: [LogGuard]}
    ]),
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule
  ],
  providers: [AuthService, ApiService, AuthGuard, LogGuard, SuperGuard, LoginGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
