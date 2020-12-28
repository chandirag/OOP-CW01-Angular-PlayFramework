import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RouteExampleComponent } from './route-example/route-example.component';

import { AppService } from './app.service';
import { AppHttpInterceptorService } from './http-interceptor.service';
import { NavbarComponent } from './navbar/navbar.component';
import { MatchesPlayedComponent } from './matches-played/matches-played.component';
import { PremiereLeagueTableComponent } from './premiere-league-table/premiere-league-table.component';
import { ModifyClubsComponent } from './modify-clubs/modify-clubs.component';

import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatSelectModule } from "@angular/material/select";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatChipsModule } from "@angular/material/chips";
import { MatTableModule } from "@angular/material/table";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatButtonToggleModule } from "@angular/material/button-toggle";

const routes: Routes = [
  {
    path: 'java',
    component: RouteExampleComponent,
    data: { technology: 'Java' }
  },
  {
    path: 'play',
    component: RouteExampleComponent,
    data: { technology: 'Play' }
  },
  {
    path: 'angular',
    component: RouteExampleComponent,
    data: { technology: 'Angular' }
  },
  {
    path: 'clubs',
    component: ModifyClubsComponent,
  },
  {
    path: 'matches',
    component: MatchesPlayedComponent,
  },
  {
    path: 'leagueStandings',
    component: PremiereLeagueTableComponent,
  },
  {
    path: '**',
    redirectTo: '/',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    RouteExampleComponent,
    NavbarComponent,
    MatchesPlayedComponent,
    PremiereLeagueTableComponent,
    ModifyClubsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'Csrf-Token',
      headerName: 'Csrf-Token',
    }),
    RouterModule.forRoot(routes),
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatCheckboxModule,
    MatChipsModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatButtonToggleModule
  ],
  providers: [
    AppService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
