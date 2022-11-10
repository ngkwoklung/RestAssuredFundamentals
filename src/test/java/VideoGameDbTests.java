import config.VideoGameConfig;
import config.VideoGamesEndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGameDbTests extends VideoGameConfig {

    @Test
    public void getAllGames() {
        given().
        when().get(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void createNewGameByJSON() {
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"MyNewGame\",\n" +
                "  \"releaseDate\": \"2019-12-03T10:02:58.768Z\",\n" +
                "  \"reviewScore\": 88,\n" +
                "  \"category\": \"Shooter\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given()
                .body(gameBodyJson).
        when()
                .post(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void createNewGameByXML() {
        String gameBodyXml = "  <videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>12</id>\n" +
                "    <name>Resident Evil 8</name>\n" +
                "    <releaseDate>2005-10-01T00:00:00+01:00</releaseDate>\n" +
                "    <reviewScore>99</reviewScore>\n" +
                "  </videoGame>";

        given()
                .body(gameBodyXml)
                .header("accept","application/xml")
                .header("Content-Type","application/xml").
        when()
                .post(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void updateGame() {
        String gameBodyJson = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"MyNewGame\",\n" +
                "  \"releaseDate\": \"2022-11-09T16:15:48.530Z\",\n" +
                "  \"reviewScore\": 77,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Universal\"\n" +
                "}";

        given()
                .body(gameBodyJson).
        when()
                .put("videogames/1").
        then();
    }

    @Test
    public void deleteGame() {
        given().
        when()
                .delete("videogames/1").
        then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId",5).
        when()
                .get(VideoGamesEndPoints.SINGLE_VIDEO_GAME).
        then();
    }

    @Test
    public void testVideoGameSerializationByJSON() {
        VideoGameDTO videoGame = new VideoGameDTO(99, "2018-04-04", "My Awesome Game"
                , "Mature", 15, "Shooter");

        given()
                .body(videoGame).
        when().
                post(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then();
    }
}
