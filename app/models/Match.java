package models;

import java.io.Serializable;

public class Match implements Serializable, Comparable<Match> {
    private Date datePlayed;
    private FootballClub team1;
    private int team1Score;
    private FootballClub team2;
    private int team2Score;

    public Match() {}
    public Match(Date datePlayed, FootballClub team1, int team1Score, FootballClub team2, int team2Score) {
        this.datePlayed = datePlayed;
        this.team1 = team1;
        this.team1Score = team1Score;
        this.team2 = team2;
        this.team2Score = team2Score;
    }

    public Date getDatePlayed() { return datePlayed; }
    public void setDatePlayed(Date datePlayed) { this.datePlayed = datePlayed; }

    public FootballClub getTeam1() { return team1; }
    public void setTeam1(FootballClub team1) { this.team1 = team1; }

    public int getTeam1Score() { return team1Score; }
    public void setTeam1Score(int team1Score) { this.team1Score = team1Score; }

    public FootballClub getTeam2() { return team2; }
    public void setTeam2(FootballClub team2) { this.team2 = team2; }

    public int getTeam2Score() { return team2Score; }
    public void setTeam2Score(int team2Score) { this.team2Score = team2Score; }

    @Override
    public int compareTo(Match m) {
        // Compare dates played of the two matches
        return getDatePlayed().compareTo(m.getDatePlayed());
    }
}
