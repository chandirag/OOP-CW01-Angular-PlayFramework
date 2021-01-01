import { Date } from "./date";

export interface RegisteredFootballClub {
  clubName: string;
  clubLocation: string;
  dateFounded: Date;
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
