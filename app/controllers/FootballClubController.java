package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Date;
import models.PremiereLeagueManager;
import play.libs.Json;
import play.mvc.Result;

import static play.mvc.Results.ok;


public class FootballClubController {

    public Result createClub(String clubName, String clubLocation, String headCoach, String day, String month, String year) {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");
        Date dateFounded = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));


        premiereLeagueManager.addNewClub(clubName, clubLocation, dateFounded, headCoach);
        premiereLeagueManager.saveState("data.ser");

        JsonNode jsonNode = Json.toJson(premiereLeagueManager.getClubs());
        return ok(jsonNode).as("application/json");
    }

    public Result deleteExistingClub(String existingClubName) {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        premiereLeagueManager.deleteClub(existingClubName);
        premiereLeagueManager.saveState("data.ser");

        JsonNode jsonNode = Json.toJson(premiereLeagueManager.getClubs());
        return ok(jsonNode).as("application/json");
    }

    public Result getClubs() {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        JsonNode jsonNode = Json.toJson(premiereLeagueManager.getClubs());
        return ok(jsonNode).as("application/json");
    }

}
