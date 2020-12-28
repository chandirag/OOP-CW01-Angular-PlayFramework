package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import entities.PremiereLeagueManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class MatchController extends Controller {
    public Result getMatches() {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
        premiereLeagueManager.restorePreviousState("data.ser");

        JsonNode jsonNode = Json.toJson(premiereLeagueManager.getMatches());
        return ok(jsonNode).as("application/json");
    }
}
