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
