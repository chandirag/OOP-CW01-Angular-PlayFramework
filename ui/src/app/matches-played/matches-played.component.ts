import {Component, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PlayedMatch} from "../interfaces/played-match";
import {RegisteredFootballClub} from "../interfaces/registered-football-club";

@Component({
  selector: 'app-matches-played',
  templateUrl: './matches-played.component.html',
  styleUrls: ['./matches-played.component.css']
})
export class MatchesPlayedComponent implements OnInit {
  addMatchForm: FormGroup;
  filterMatchForm: FormGroup;

  public show: boolean = true;
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
      dayPlayed: ['', [Validators.required, Validators.min(1), Validators.max(31), Validators.pattern("^[0-9]*$")]],
      monthPlayed: ['', [Validators.required, Validators.min(1), Validators.max(12), Validators.pattern("^[0-9]*$")]],
      yearPlayed: ['', [Validators.required, Validators.minLength(4), Validators.pattern("^[0-9]*$")]],
      team1Name: [null, [Validators.required]],
      team1Score: ['', [Validators.required, Validators.min(0), Validators.pattern("^[0-9]*$")]],
      team2Name: [null, [Validators.required]],
      team2Score: ['', [Validators.required, Validators.min(0), Validators.pattern("^[0-9]*$")]]
    });

    this.filterMatchForm = this.formBuilder.group({
      dayPlayed: ['', [Validators.required, Validators.min(1), Validators.max(31), Validators.pattern("^[0-9]*$")]],
      monthPlayed: ['', [Validators.required, Validators.min(1), Validators.max(12), Validators.pattern("^[0-9]*$")]],
      yearPlayed: ['', [Validators.required, Validators.minLength(4), Validators.pattern("^[0-9]*$")]]
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
      this.addMatchForm.get('yearPlayed').value).subscribe((data: any) => { this.matchesList = data; })
    this.addMatchForm.reset();
    this.addMatchForm.clearValidators();
  }

  public filterMatches(): PlayedMatch[] {
    this.matchesList = this.matchesList.filter(match =>
      match.datePlayed.day == this.filterMatchForm.get('dayPlayed').value &&
      match.datePlayed.month == this.filterMatchForm.get('monthPlayed').value &&
      match.datePlayed.year == this.filterMatchForm.get('yearPlayed').value
    );
    return this.matchesList;
  }

  public showAllMatches(): PlayedMatch[] {
    this.appService.getMatches().subscribe((data: any) => {
      this.matchesList = data;
    })
    this.filterMatchForm.reset();
    this.filterMatchForm.clearValidators();
    return this.matchesList;
  }


  get dayPlayed() { return this.addMatchForm.get('dayPlayed'); }
  get monthPlayed() { return this.addMatchForm.get('monthPlayed'); }
  get yearPlayed() { return this.addMatchForm.get('yearPlayed'); }
  get team1Name() { return this.addMatchForm.get('team1Name'); }
  get team1Score() { return this.addMatchForm.get('team1Score'); }
  get team2Name() { return this.addMatchForm.get('team2Name'); }
  get team2Score() { return this.addMatchForm.get('team2Score'); }

  // Getter for list which contains the registered clubs
  public getClubs(): RegisteredFootballClub[] { return this.clubsList; }

  // Method which adds a random match between existing matches
  public addRandomMatch(): void {
    this.appService.addRandomMatch().subscribe((data:any) => { this.matchesList = data; })
    this.addMatchForm.reset();              // Resets the form
    this.addMatchForm.clearValidators();    // Clears validators
  }

  // Methods that sorts the list to sort the matches table by date
  public sortTableByDatesAscending() {
    this.matchesList.sort(function(m1, m2) {
      let result = m1.datePlayed.year - m2.datePlayed.year;
      if (result == 0) {
        result = m1.datePlayed.month - m2.datePlayed.month;
        if (result == 0) {
          result = m1.datePlayed.day - m2.datePlayed.day;
          return result;
        }
        return result;
      }
      return result;
    })
  }
  public sortTableByDatesDescending() {
    this.matchesList.sort(function(m1, m2) {
      let result = m2.datePlayed.year - m1.datePlayed.year;
      if (result == 0) {
        result = m2.datePlayed.month - m1.datePlayed.month;
        if (result == 0) {
          result = m2.datePlayed.day - m1.datePlayed.day;
          return result;
        }
        return result;
      }
      return result;
    })
  }

}
