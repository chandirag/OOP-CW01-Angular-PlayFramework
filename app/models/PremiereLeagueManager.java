package models;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PremiereLeagueManager implements LeagueManager, Serializable {
    private int pointsForWins = 3;
    private int pointsForDraws = 1;
    private List<FootballClub> clubs = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    // Method which creates a new club from input and adds it to the clubs list
    @Override
    public void addNewClub(String clubName, String clubLocation, Date dateFounded, String headCoach) {
        FootballClub footballClub = new FootballClub(clubName, clubLocation, dateFounded, headCoach);
        clubs.add(footballClub);
        clubs.sort(Collections.reverseOrder());
    }

    // Method which deletes a club, with the name passed as a parameter, from the clubs list
    @Override
    public String deleteClub(String clubName) {
        for (FootballClub club: clubs)  {
            if (club.getClubName().equalsIgnoreCase(clubName)) {
                clubs.remove(club);
                return clubName + " removed from the database.";
            }
        }
        this.saveState("data.ser");
        return "A club with that name was not found.\n";
    }

    // Method which which displays all stats for a given club
    @Override
    public String displayStatsForClub(String clubName) {
        for (FootballClub club: clubs)  {
            if (club.getClubName().equalsIgnoreCase(clubName)) {
                return "\n" + club.getClubName() + "\n------------------------\n" +
                        "Club Location            :  " + club.getClubLocation() + "\n" +
                        "Date Founded             :  " + club.getDateFounded().toString() + "\n" +
                        "Head Coach               :  " + club.getHeadCoach() + "\n" +
                        "Club points              :  " + club.getClubPoints() + "\n" +
                        "Number of matches played :  " + club.getNoOfMatchesPlayed() + "\n" +
                        "Number of wins           :  " + club.getNoOfWins() + "\n" +
                        "Number of losses         :  " + club.getNoOfLosses() + "\n" +
                        "Number of draws          :  " + club.getNoOfDraws() + "\n" +
                        "Goals Scored             :  " + club.getGoalsScored() + "\n" +
                        "Goals Received           :  " + club.getGoalsReceived() + "\n" +
                        "Goal Difference          :  " + club.getGoalDifference() + "\n";
            }
        }
        return "A club with that name was not found.\n";
    }

    // Method which displays the premiere league table in the console with data from currently added clubs
    @Override
    public String displayPremierLeagueTable() {
        StringBuilder output = new StringBuilder();
        output.append("\n--------------------------------- Premiere League Championship ---------------------------------------\n");
        String tableHeader = String.format("|%-3s |%-20s |%-10s |%-7s |%-7s |%-7s |%-7s |%-7s |%-7s |%-7s|",
                "Pos", "Club", "Played", "Wins", "Draws", "Losses", "GS", "GR", "GD", "Pts");
        output.append(tableHeader).append("\n");
        clubs.sort(Collections.reverseOrder());
        int i = 1;
        for (FootballClub club : clubs) {
            String result = String.format("|%-3d |%-20s |%-10d |%-7d |%-7d |%-7d |%-7d |%-7d |%-7d |%-7d|",
                    i, club.getClubName(), club.getNoOfMatchesPlayed(), club.getNoOfWins(), club.getNoOfDraws(),
                    club.getNoOfLosses(), club.getGoalsScored(), club.getGoalsReceived(), club.getGoalDifference(), club.getClubPoints());
            output.append(result).append("\n");
            i++;
        }
        output.append("------------------------------------------------------------------------------------------------------\n");
        return output.toString();
    }

    // Method which creates a played match and adds it to the matches list
    @Override
    public void addMatchToPremierLeague(Date matchDate, String team1Name, int team1Score, String team2Name, int team2Score) {
        Match match = new Match(matchDate, checkForClub(team1Name), team1Score, checkForClub(team2Name), team2Score);
        matches.add(match);
        updateScores(match);
        clubs.sort(Collections.reverseOrder());
        this.saveState("data.ser");
    }

    // Method which restores a premiereLeagueManager object with serialized data from a file
    @Override
    @SuppressWarnings("unchecked") // Disables compilation warning when casting object to Arraylist
    public void restorePreviousState(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object clubsArrayObject = objectInputStream.readObject();
            Object matchesArrayObject =  objectInputStream.readObject();
            clubs = (ArrayList<FootballClub>) clubsArrayObject;
            matches = (ArrayList<Match>) matchesArrayObject;
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException | EOFException e) {
            new File(fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method which saves serialized data to a file
    @Override
    public void saveState(String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(clubs);
            objectOutputStream.writeObject(matches);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public List<FootballClub> getClubs() { return this.clubs; }
    public List<Match> getMatches() { return matches; }

    public int getPointsForWins() { return pointsForWins; }
    public void setPointsForWins(int pointsForWins) { this.pointsForWins = pointsForWins; }

    public int getPointsForDraws() { return pointsForDraws; }
    public void setPointsForDraws(int pointsForDraws) { this.pointsForDraws = pointsForDraws; }

    // Method which checks if a club exists in the clubs list when passed a String value of clubName
    private FootballClub checkForClub(String clubName) {
        for (FootballClub club: clubs) {
            // If club exists return the FootballClub object
            if (clubName.equalsIgnoreCase(club.getClubName())) {
                return club;
            }
        }
        return null;
    }

    // Method which takes a match object as a parameter and updates scores for both clubs accordingly
    private void updateScores(Match match) {
        FootballClub club1 = match.getTeam1();
        FootballClub club2 = match.getTeam2();

        // Increment matches played for both teams
        club1.incrementMatchesPlayed();
        club2.incrementMatchesPlayed();

        // Update Goals Scored
        club1.incrementGoalsScoredBy(match.getTeam1Score());
        club2.incrementGoalsScoredBy(match.getTeam2Score());

        // Update Goals Received
        club1.incrementGoalsReceivedBy(match.getTeam2Score());
        club2.incrementGoalsReceivedBy(match.getTeam1Score());

        // Match Outcome: Update wins, losses, draws
        if (match.getTeam1Score() > match.getTeam2Score()) {
            // If team 1 wins:
            club1.incrementNoOfWins();
            club2.incrementNoOfLosses();
            club1.setClubPoints(club1.getClubPoints() + pointsForWins);

        } else if (match.getTeam1Score() < match.getTeam2Score()) {
            // If team 2 wins:
            club2.incrementNoOfWins();
            club1.incrementNoOfLosses();
            club2.setClubPoints(club2.getClubPoints() + pointsForWins);

        } else if (match.getTeam1Score() == match.getTeam2Score()) {
            // If match is a draw
            club1.incrementNoOfDraws();
            club2.incrementNoOfDraws();
            club1.setClubPoints(club1.getClubPoints() + pointsForDraws);
            club2.setClubPoints(club2.getClubPoints() + pointsForDraws);

        }
    }

}
