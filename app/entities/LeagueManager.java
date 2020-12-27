package entities;

import java.io.IOException;

public interface LeagueManager {
    void addNewClub(String clubName, String clubLocation, Date dateFounded, String headCoach);
    String deleteClub(String clubName);
    String displayStatsForClub(String clubNName);
    String displayPremierLeagueTable();
    void addMatchToPremierLeague(Date matchDate, String team1Name, int team1Score, String team2Name, int team2Score);
    void restorePreviousState(String fileName);
    void saveState(String fileName) throws IOException;
}
