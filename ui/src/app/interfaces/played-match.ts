import { Date } from "./date";
import {RegisteredFootballClub} from "./registered-football-club";

export interface PlayedMatch {
  datePlayed: Date;
  team1: RegisteredFootballClub;
  team1Score: number;
  team2: RegisteredFootballClub;
  team2Score: number;
}
