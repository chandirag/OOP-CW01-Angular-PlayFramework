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
