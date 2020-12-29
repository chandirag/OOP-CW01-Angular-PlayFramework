package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import entities.Date;
import entities.FootballClub;
import entities.PremiereLeagueManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MatchController extends Controller {

    public Result addMatch(String team1Name, String score1, String team2Name, String score2, String dayP, String monthP, String yearP) {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");
        Date datePlayed = new Date(Integer.parseInt(yearP), Integer.parseInt(monthP), Integer.parseInt(dayP));

        premiereLeagueManager.addMatchToPremierLeague(datePlayed, team1Name, Integer.parseInt(score1), team2Name, Integer.parseInt(score2));
        try {
            premiereLeagueManager.saveState("data.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode jsonNode = Json.toJson(new AppSummary(String.valueOf(premiereLeagueManager.getClubs().size())));
        return ok(jsonNode).as("application/json");
    }

    public Result addRandomMatch() {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");
        Random random = new Random();

        // Generate random date
        int year = 2020; // Fixed year for random date
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        Date datePlayed = new Date(year, month, day);

        // Get random number between 0 and the max limit of the clubs array
        int clubsArraySize = premiereLeagueManager.getClubs().size();

        // Select team 1 randomly from clubs list
        int randomNumber1 = random.nextInt(clubsArraySize);
        FootballClub team1 = premiereLeagueManager.getClubs().get(randomNumber1);
        String team1Name = team1.getClubName();

        // Selecting team 2:
        // Copy clubs array into a new arraylist
        ArrayList<FootballClub> tempList = new ArrayList<>(premiereLeagueManager.getClubs());
        // Remove randomly selected Team 1 from that array list
        tempList.remove(team1);
        // Randomly select Team 2 from the new list
        int randomNumber2 = random.nextInt(tempList.size());
        FootballClub team2 = tempList.get(randomNumber2);
        String team2Name = team2.getClubName();

        // Get random score between 0-5
        // Assign it to the teamScore
        int team1Score = random.nextInt(6);
        int team2Score = random.nextInt(6);

        // Pass all variables to addMatchToPremiereLeague()
        premiereLeagueManager.addMatchToPremierLeague(datePlayed, team1Name, team1Score, team2Name, team2Score);

        try {
            premiereLeagueManager.saveState("data.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode jsonNode = Json.toJson(new AppSummary(String.valueOf(premiereLeagueManager.getClubs().size())));
        return ok(jsonNode).as("application/json");
    }

    public Result getMatches() {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        JsonNode jsonNode = Json.toJson(premiereLeagueManager.getMatches());
        return ok(jsonNode).as("application/json");
    }
}
