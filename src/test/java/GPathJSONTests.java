import config.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends FootballApiConfig {

    @Test
    public void extractMapOfElementsWithFind() {

        Response response = get("competitions/2021/teams");

        Map<String, ?> allTeamDataForSingleTeam = response.path
                ("teams.find { it.name == 'Manchester United FC' }");

        System.out.println("Map of Team Data = " + allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind() {
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find { it.id == 5530}.name");
        System.out.println("Name of player = " + certainPlayer);
    }

    @Test
    public void extractListOfValuesWithFindAll() {
        Response response = get("teams/57");
        List<String> playerNames = response.path("squad.findAll { it.position == 'Goalkeeper' }.name");
        System.out.println("List of players " + playerNames);
    }

    @Test
    public void extractSingleValueWithHighestNumber() {
        Response response = get("teams/57");
        String playerName = response.path("squad.max { it.id }.name");
        System.out.println("Player with highest id number = " + playerName);
    }

    @Test
    public void extractMultipleValuesAndSumThem() {
        Response response = get("teams/57");
        int sumOfIds = response.path("squad.collect { it.id }.sum()");
        System.out.println("Sum of all IDs = " + sumOfIds);
    }

    @Test
    public void extractMapOfObjectWithFindAndFindAll() {
        Response response = get("teams/57");
        Map<String, ?> playerOfCertainPosition = response.path(
                "squad.findAll { it.position == 'Defence'}.find { it.nationality == 'Japan' }"
        );
        System.out.println("Details of players: " + playerOfCertainPosition);
    }

    @Test
    public void extractMapOfObjectWithFindAndFindAllWithParameters() {

        String position = "Defence";
        String nationality = "Japan";

        Response response = get("teams/57");
        Map<String, ?> playerOfCertainPosition = response.path(
                "squad.findAll { it.position == '%s'}.find { it.nationality == '%s' }",
                position, nationality);

        System.out.println("Details of players: " + playerOfCertainPosition);
    }

    @Test
    public void extractMultiplePlayers() {

        String position = "Offence";
        String nationality = "England";

        Response response = get("teams/57");
        ArrayList<Map<String, ?>> allPlayersCertainNation = response.path(
                "squad.findAll { it.position == '%s'}.findAll { it.nationality == '%s' }",
                position, nationality
        );
        System.out.println("All players: " + allPlayersCertainNation);
    }
}
