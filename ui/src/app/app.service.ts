import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/index';

/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {
  private serviceUrl = '/api/summary';
  private dataPostTestUrl = '/api/postTest';

  private noOfClubsUrl = '/api/getNumberOfClubs';
  private createClubUrl = '/api/createClub';
  private deleteClubUrl = '/api/deleteExistingClub';
  private clubsUrl = '/api/getClubs';
  private matchesUrl = '/api/getMatches';
  private addMatchUrl = '/api/addMatch';
  private addRandomMatchUrl = '/api/addRandomMatch';

  constructor(private http: HttpClient) {
  }

  /**
   * Makes a http get request to retrieve the welcome message from the backend service.
   */
  public getWelcomeMessage() {
    return this.http.get(this.serviceUrl).pipe(
      map(response => response)
    );
  }

  /**
   * Makes a http post request to send some data to backend & get response.
   */
  public sendData(): Observable<any> {
    return this.http.post(this.dataPostTestUrl, {});
  }





  public getNoOfClubs(): Observable<any> {
    return this.http.get(this.noOfClubsUrl, {})
  }

  public createNewClub(clubName, clubLocation, headCoach, day, month, year): Observable<any> {
    return this.http.post(this.createClubUrl + '/' + clubName + '/' + clubLocation + '/' + headCoach + '/' + day + '/' + month + '/' + year, {})
  }

  public deleteExistingClub(existingClubName): Observable<any> {
    return this.http.post(this.deleteClubUrl + '/' + existingClubName, {})
  }

  public addMatch(team1Name, score1, team2Name, score2, dayP, monthP, yearP): Observable<any> {
    return this.http.post(this.addMatchUrl + '/' + team1Name + '/' + score1 + '/' + team2Name + '/' + score2 + '/' + dayP + '/' + monthP + '/' + yearP, {})
  }

  public addRandomMatch() {
    return this.http.post(this.addRandomMatchUrl, {})
  }

  public getClubs(): Observable<any> {
    return this.http.get(this.clubsUrl, {})
  }

  public getMatches(): Observable<any> {
    return this.http.get(this.matchesUrl, {})
  }
}
