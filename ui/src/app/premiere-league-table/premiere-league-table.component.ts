import { Component, OnInit } from '@angular/core';
import { AppService } from "../app.service";
import RegisteredFootballClub = RegisteredFootballClubList.RegisteredFootballClub;

// export interface RegisteredFootballClub {
//   pos: number,
//   clubName: string,
//   playedMatches: number,
//   wins: number,
//   draws: number,
//   losses: number,
//   goalsScored: number,
//   goalsReceived: number,
//   goalDifference: number,
//   points: number
// }
//
// const DATA: RegisteredFootballClub[] = [
//   {pos: 1, clubName: 'Manchester', playedMatches: 0, wins: 0, draws: 0, losses: 0, goalsScored: 0, goalsReceived: 0, goalDifference: 0, points: 0},
//   {pos: 2, clubName: 'Barcelona', playedMatches: 0, wins: 0, draws: 0, losses: 0, goalsScored: 0, goalsReceived: 0, goalDifference: 0, points: 0}
//
//   // {pos: 1, clubName: 'Manchester'},
//   // {pos: 2, clubName: 'Barcelona'}
// ];

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

  // displayedColumns: string[] = ['pos', 'clubName', 'playedMatches', 'wins', 'draws', 'losses', 'goalsScored', 'goalsReceived', 'goalDifference', 'points']
  // // displayedColumns: string[] = ['pos', 'clubName']
  // dataSource = DATA;

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

}
