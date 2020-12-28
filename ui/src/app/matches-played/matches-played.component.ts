import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { PlayedMatch } from "../interfaces/played-match";
import {RegisteredFootballClub} from "../interfaces/registered-football-club";

@Component({
  selector: 'app-matches-played',
  templateUrl: './matches-played.component.html',
  styleUrls: ['./matches-played.component.css']
})
export class MatchesPlayedComponent implements OnInit {
  addMatchForm: FormGroup;
  filterMatchForm: FormGroup;

  matchesList: PlayedMatch[];
  clubsList: RegisteredFootballClub[];


  constructor(private appService: AppService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.appService.getClubs().subscribe((data: any) => {
      this.clubsList = data;
    })

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

  public addMatch(): void {
    this.appService.addMatch(
      this.addMatchForm.get('team1Name').value,
      this.addMatchForm.get('team1Score').value,
      this.addMatchForm.get('team2Name').value,
      this.addMatchForm.get('team2Score').value,
      this.addMatchForm.get('dayPlayed').value,
      this.addMatchForm.get('monthPlayed').value,
      this.addMatchForm.get('yearPlayed').value).subscribe((data: any) => {})

    this.appService.getMatches().subscribe((data: any) => {
      this.matchesList = data;
    })
  }

  get dayPlayed() { return this.addMatchForm.get('dayPlayed'); }
  get monthPlayed() { return this.addMatchForm.get('monthPlayed'); }
  get yearPlayed() { return this.addMatchForm.get('yearPlayed'); }
  get team1Name() { return this.addMatchForm.get('team1Name'); }
  get team1Score() { return this.addMatchForm.get('team1Score'); }
  get team2Name() { return this.addMatchForm.get('team2Name'); }
  get team2Score() { return this.addMatchForm.get('team2Score'); }

  public getClubs(): RegisteredFootballClub[] {
    return this.clubsList;
  }
}
