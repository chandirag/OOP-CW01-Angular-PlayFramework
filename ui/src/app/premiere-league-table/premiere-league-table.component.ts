import { Component, OnInit } from '@angular/core';
import { AppService } from "../app.service";
import { RegisteredFootballClub } from "../interfaces/registered-football-club";

@Component({
  selector: 'app-premiere-league-table',
  templateUrl: './premiere-league-table.component.html',
  styleUrls: ['./premiere-league-table.component.css']
})
export class PremiereLeagueTableComponent implements OnInit {
  clubsList: RegisteredFootballClub[];

  constructor(private appService: AppService) { }

  ngOnInit() {
    this.appService.getClubs().subscribe((data: any) => {
      this.clubsList = data;
    })
  }

  public getClubs(): RegisteredFootballClub[] {
    return this.clubsList;
  }

  public sortTableByPointsDescending() {
    this.clubsList.sort(function(a, b) {
      return b.clubPoints - a.clubPoints;
    })
  }

  public sortTableByPointsAscending() {
    this.clubsList.sort(function(a, b) {
      return a.clubPoints - b.clubPoints;
    })
  }


  public sortTableByGoalsScoredDescending() {
    this.clubsList.sort(function(a, b) {
      return b.goalsScored - a.goalsScored;
    })
  }

  public sortTableByGoalsScoredAscending() {
    this.clubsList.sort(function(a, b) {
      return a.goalsScored - b.goalsScored;
    })
  }


  public sortTableByWinsDescending() {
    this.clubsList.sort(function(a, b) {
      return b.noOfWins - a.noOfWins;
    })
  }

  public sortTableByWinsAscending() {
    this.clubsList.sort(function(a, b) {
      return a.noOfWins - b.noOfWins;
    })
  }
}
