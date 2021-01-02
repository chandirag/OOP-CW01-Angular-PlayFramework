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
    // Makes a http GET request to get the currently registered club details as a JSON response
    // Assigns the JSON response (which is of type RegisteredFootballClub) to the array
    this.appService.getClubs().subscribe((data: any) => {
      this.clubsList = data;
    })
  }

  // Getter for list containing clubs
  public getClubs(): RegisteredFootballClub[] {
    return this.clubsList;
  }

  // Methods that sorts the list to sort the league table by points
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

  // Methods that sorts the list to sort the league table by goals scored
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

  // Methods that sorts the list to sort the league table by wins
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
