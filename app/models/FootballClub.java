package models;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable, Comparable<FootballClub> {
    private int noOfMatchesPlayed;
    private int clubPoints;
    private int noOfWins;
    private int noOfLosses;
    private int noOfDraws;
    private int goalsReceived;
    private int goalsScored;

    public FootballClub() {}
    public FootballClub(String clubName, String clubLocation, Date dateFounded, String headCoach) {
        super(clubName, clubLocation, dateFounded, headCoach);
    }
    public FootballClub(String clubName, String clubLocation, Date dateFounded, String headCoach, int noOfMatchesPlayed,
                        int clubPoints, int noOfWins, int noOfLosses, int noOfDraws, int goalsReceived, int goalsScored) {
        super(clubName, clubLocation, dateFounded, headCoach);
        this.noOfMatchesPlayed = noOfMatchesPlayed;
        this.clubPoints = clubPoints;
        this.noOfWins = noOfWins;
        this.noOfLosses = noOfLosses;
        this.noOfDraws = noOfDraws;
        this.goalsReceived = goalsReceived;
        this.goalsScored = goalsScored;
    }
    public FootballClub(String clubName, int noOfMatchesPlayed, int clubPoints, int noOfWins, int noOfDraws, int noOfLosses,
                        int goalsScored, int goalsReceived,  int goalDifference) {
        setClubName(clubName);
        this.noOfMatchesPlayed = noOfMatchesPlayed;
        this.clubPoints = clubPoints;
        this.noOfWins = noOfWins;
        this.noOfLosses = noOfLosses;
        this.noOfDraws = noOfDraws;
        this.goalsReceived = goalsReceived;
        this.goalsScored = goalsScored;
    }

    public int getNoOfMatchesPlayed() { return noOfMatchesPlayed; }
    public void setNoOfMatchesPlayed(int noOfMatchesPlayed) {
        if (noOfMatchesPlayed >= 0) this.noOfMatchesPlayed = noOfMatchesPlayed;
        else throw new IllegalArgumentException("No of matches played should be a positive integer.");
    }
    public void incrementMatchesPlayed() { this.noOfMatchesPlayed++; }

    public int getClubPoints() { return clubPoints; }
    public void setClubPoints(int clubPoints) {
        if (clubPoints >= 0) this.clubPoints = clubPoints;
        else throw new IllegalArgumentException("Club points should be a positive integer");
    }

    public int getNoOfWins() { return noOfWins; }
    public void setNoOfWins(int noOfWins) {
        if (noOfWins >= 0) this.noOfWins = noOfWins;
        else throw new IllegalArgumentException("No. of wins should be a positive integer");
    }
    public void incrementNoOfWins() { this.noOfWins++; }

    public int getNoOfLosses() { return noOfLosses; }
    public void setNoOfLosses(int noOfLosses) {
        if (noOfLosses >= 0) this.noOfLosses = noOfLosses;
        else throw new IllegalArgumentException("No. of losses should be a positive integer");
    }
    public void incrementNoOfLosses() { this.noOfLosses++; }

    public int getNoOfDraws() { return noOfDraws; }
    public void setNoOfDraws(int noOfDraws) {
        if (noOfDraws >= 0) this.noOfDraws = noOfDraws;
        else throw new IllegalArgumentException("No. of draws should be a positive integer");
    }
    public void incrementNoOfDraws() { this.noOfDraws++; }

    public int getGoalsReceived() { return goalsReceived; }
    public void setGoalsReceived(int goalsReceived) {
        if (goalsReceived >= 0) this.goalsReceived = goalsReceived;
        else throw new IllegalArgumentException("No. of goals received should be a positive integer");
    }
    public void incrementGoalsReceivedBy(int goalsReceivedInMatch) { this.goalsReceived += goalsReceivedInMatch; }

    public int getGoalsScored() { return goalsScored; }
    public void setGoalsScored(int goalsScored) {
        if (goalsScored >= 0) this.goalsScored = goalsScored;
        else throw new IllegalArgumentException("No. of losses should be a positive integer");
    }
    public void incrementGoalsScoredBy(int goalsScoredInMatch) { this.goalsScored += goalsScoredInMatch; }

    public int getGoalDifference() { return this.getGoalsScored() - this.getGoalsReceived(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return noOfMatchesPlayed == that.noOfMatchesPlayed &&
                clubPoints == that.clubPoints &&
                noOfWins == that.noOfWins &&
                noOfLosses == that.noOfLosses &&
                noOfDraws == that.noOfDraws &&
                goalsReceived == that.goalsReceived &&
                goalsScored == that.goalsScored;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), noOfMatchesPlayed, clubPoints, noOfWins, noOfLosses, noOfDraws, goalsReceived, goalsScored);
    }

    @Override
    public int compareTo(FootballClub footballClub) {
        // First compare the club points of each club
        int result = this.clubPoints - footballClub.getClubPoints();
        if (result == 0) {
            // If club points are equal compare the goal difference
            return this.getGoalDifference() - footballClub.getGoalDifference();
        } else {
            return result;
        }
    }

    @Override
    public String toString() {
        return this.getClubName();
    }
}
