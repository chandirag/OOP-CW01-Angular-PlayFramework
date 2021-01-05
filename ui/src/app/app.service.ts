import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable()
export class AppService {

  private createClubUrl = '/api/createClub';
  private deleteClubUrl = '/api/deleteExistingClub';
  private clubsUrl = '/api/getClubs';
  private matchesUrl = '/api/getMatches';
  private addMatchUrl = '/api/addMatch';
  private addRandomMatchUrl = '/api/addRandomMatch';

  constructor(private http: HttpClient) {
  }

  // Makes a http POST request to send data to the backend to create a new football club
  public createNewClub(clubName, clubLocation, headCoach, day, month, year): Observable<any> {
    return this.http.post(this.createClubUrl + '/' + clubName + '/' + clubLocation + '/' + headCoach + '/' + day + '/' + month + '/' + year, {})
  }

  // Makes a http POST request to send data to the backend to delete an existing club
  public deleteExistingClub(existingClubName): Observable<any> {
    return this.http.post(this.deleteClubUrl + '/' + existingClubName, {})
  }

  // Makes a http POST request to send data to the backend to add a match between two existing clubs
  public addMatch(team1Name, score1, team2Name, score2, dayP, monthP, yearP): Observable<any> {
    return this.http.post(this.addMatchUrl + '/' + team1Name + '/' + score1 + '/' + team2Name + '/' + score2 + '/' + dayP + '/' + monthP + '/' + yearP, {})
  }

  // Makes a http POST request to add a random match between two existing clubs
  public addRandomMatch() {
    return this.http.post(this.addRandomMatchUrl, {})
  }

  // Makes a http GET request to get the currently registered club details as a JSON response
  public getClubs(): Observable<any> {
    return this.http.get(this.clubsUrl, {})
  }

  // Makes a http GET request to get the currently played matches as a JSON response
  public getMatches(): Observable<any> {
    return this.http.get(this.matchesUrl, {})
  }
}
