import { Component, OnInit } from '@angular/core';
import { AppService } from "../app.service";
import RegisteredFootballClub = RegisteredFootballClubList.RegisteredFootballClub;

declare module RegisteredFootballClubList {

  export interface DateFounded {
    year: number;
    month: number;
    day: number;
    isLeapYear: boolean;
  }

  export interface RegisteredFootballClub {
    clubName: string;
    clubLocation: string;
    dateFounded: DateFounded;
    headCoach: string;
    noOfMatchesPlayed: number;
    clubPoints: number;
    noOfWins: number;
    noOfLosses: number;
    noOfDraws: number;
    goalsReceived: number;
    goalsScored: number;
    goalDifference: number;
  }

}


@Component({
  selector: 'app-premiere-league-table',
  templateUrl: './premiere-league-table.component.html',
  styleUrls: ['./premiere-league-table.component.css']
})
export class PremiereLeagueTableComponent implements OnInit {
  clubsList: RegisteredFootballClub[];
  positions: number[];

  constructor(private appService: AppService) { }

  ngOnInit() {
    this.appService.getClubs().subscribe((data: any) => {
      this.clubsList = data;
    })
  }

  public getClubs(): RegisteredFootballClub[] {
    return this.clubsList;
  }

  public createPosInts() {
    let clubsArraySize: number = this.clubsList.length;
    for(let i = 0; i <= clubsArraySize; i++) {
      this.positions.concat(i);
    }
  }

  public getPosInts(): number {
    let pos: number = this.positions[0];
    this.positions.shift();
    return pos;
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
