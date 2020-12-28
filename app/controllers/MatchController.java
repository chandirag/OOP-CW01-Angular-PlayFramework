package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import entities.Date;
import entities.PremiereLeagueManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;

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

    public Result getMatches() {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        JsonNode jsonNode = Json.toJson(premiereLeagueManager.getMatches());
        return ok(jsonNode).as("application/json");
    }
}
