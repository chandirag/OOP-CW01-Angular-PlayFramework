package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import entities.Date;
import entities.PremiereLeagueManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;

public class FootballClubController extends Controller {

    public Result createClub(String clubName, String clubLocation, String headCoach, String day, String month, String year) {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");
        Date dateFounded = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));


        premiereLeagueManager.addNewClub(clubName, clubLocation, dateFounded, headCoach);
        try {
            premiereLeagueManager.saveState("data.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode jsonNode = Json.toJson(new AppSummary(String.valueOf(premiereLeagueManager.getClubs().size())));
        return ok(jsonNode).as("application/json");
    }

    public Result deleteExistingClub(String existingClubName) {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        premiereLeagueManager.deleteClub(existingClubName);

        try {
            premiereLeagueManager.saveState("data.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode jsonNode = Json.toJson(new AppSummary(String.valueOf(premiereLeagueManager.getClubs().size())));
        return ok(jsonNode).as("application/json");
    }

    public Result getNumber() {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        JsonNode jsonNode = Json.toJson(new AppSummary(String.valueOf(premiereLeagueManager.getClubs().size())));
        return ok(jsonNode).as("application/json");
    }

    public Result getClubs() {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        JsonNode jsonNode = Json.toJson(premiereLeagueManager.getClubs());
        return ok(jsonNode).as("application/json");
    }
}
