package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PremiereLeagueManager implements LeagueManager, Serializable {
    int pointsForWins = 3;
    int pointsForDraws = 1;
    private ArrayList<FootballClub> clubs = new ArrayList<>();
    private ArrayList<Match> matches = new ArrayList<>();

    @Override
    public void addNewClub(String clubName, String clubLocation, Date dateFounded, String headCoach) {
        FootballClub footballClub = new FootballClub(clubName, clubLocation, dateFounded, headCoach);
        clubs.add(footballClub);
        clubs.sort(Collections.reverseOrder());
    }

    @Override
    public String deleteClub(String clubName) {
        for (FootballClub club: clubs)  {
            if (club.getClubName().equalsIgnoreCase(clubName)) {
                clubs.remove(club);
                return clubName + " removed from the database.";
            }
        }

        try {
            this.saveState("data.ser");
        } catch (IOException e){
            return "File not found";
        }

        return "A club with that name was not found.\n";
    }

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


    @Override
    public void addMatchToPremierLeague(Date matchDate, String team1Name, int team1Score, String team2Name, int team2Score) {
        Match match = new Match(matchDate, checkForClub(team1Name), team1Score, checkForClub(team2Name), team2Score);
        matches.add(match);
        updateScores(match);
        clubs.sort(Collections.reverseOrder());
    }

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
        } catch (FileNotFoundException | EOFException e) {
            File file = new File(fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveState(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(clubs);
        objectOutputStream.writeObject(matches);
        objectOutputStream.close();
        fileOutputStream.close();
    }

//    public void resetState() {
//
//    }

    // ----------------------------- Getters ------------------------------------------------
    public ArrayList<FootballClub> getClubs() {
        return this.clubs;
    }
    public ArrayList<Match> getMatches() {
        return matches;
    }

    // ----------------------------- Utility Functions --------------------------------------

    private FootballClub checkForClub(String clubName) {
        for (FootballClub club: clubs) {
            if (clubName.equalsIgnoreCase(club.getClubName())) {
                return club;
            }
        }
        return null;
    }

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
            club1.incrementNoOfWins();
            club2.incrementNoOfLosses();
            club1.setClubPoints(club1.getClubPoints() + pointsForWins);
        } else if (match.getTeam1Score() < match.getTeam2Score()) {
            club2.incrementNoOfWins();
            club1.incrementNoOfLosses();
            club2.setClubPoints(club2.getClubPoints() + pointsForWins);
        } else if (match.getTeam1Score() == match.getTeam2Score()) {
            club1.incrementNoOfDraws();
            club2.incrementNoOfDraws();
            club1.setClubPoints(club1.getClubPoints() + pointsForDraws);
            club2.setClubPoints(club2.getClubPoints() + pointsForDraws);
        }
    }
}
