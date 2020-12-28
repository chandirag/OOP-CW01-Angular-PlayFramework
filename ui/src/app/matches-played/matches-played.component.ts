import { Component, OnInit } from '@angular/core';
import PlayedMatch = PlayedMatchesList.PlayedMatch;
import {AppService} from "../app.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

declare module PlayedMatchesList {

  export interface DatePlayed {
    year: number;
    month: number;
    day: number;
    isLeapYear: boolean;
  }

  export interface DateFounded {
    year: number;
    month: number;
    day: number;
    isLeapYear: boolean;
  }

  export interface Team1 {
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

  export interface DateFounded2 {
    year: number;
    month: number;
    day: number;
    isLeapYear: boolean;
  }

  export interface Team2 {
    clubName: string;
    clubLocation: string;
    dateFounded: DateFounded2;
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

  export interface PlayedMatch {
    datePlayed: DatePlayed;
    team1: Team1;
    team1Score: number;
    team2: Team2;
    team2Score: number;
  }

}

@Component({
  selector: 'app-matches-played',
  templateUrl: './matches-played.component.html',
  styleUrls: ['./matches-played.component.css']
})
export class MatchesPlayedComponent implements OnInit {
  addMatchForm: FormGroup;
  filterMatchForm: FormGroup;

  matchesList: PlayedMatch[];


  constructor(private appService: AppService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.appService.getMatches().subscribe((data: any) => {
      this.matchesList = data;
    })

    this.addMatchForm = this.formBuilder.group({
      dayPlayed: ['', [Validators.required, Validators.min(1), Validators.max(31)]],
      monthPlayed: ['', [Validators.required, Validators.min(1), Validators.max(12)]],
      yearPlayed: ['', [Validators.required, Validators.minLength(4)]],
      team1Name: [''],
      team1Score: [''],
      team2Name: [''],
      team2Score: ['']
    });

    this.filterMatchForm = this.formBuilder.group({
      dayPlayed: ['', [Validators.required, Validators.min(1), Validators.max(31)]],
      monthPlayed: ['', [Validators.required, Validators.min(1), Validators.max(12)]],
      yearPlayed: ['', [Validators.required, Validators.minLength(4)]]
    })
  }

  public getMatches(): PlayedMatch[] {
    return this.matchesList;
  }

}
