import config.VideoGameConfig;
import config.VideoGamesEndPoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends VideoGameConfig {

    @Test
    public void myFirstTest() {
        given()
                .log().all().
        when().get("videogames").
        then().
                log().all();
    }

    @Test
    public void myFirstTestWithEndpoint() {
        get(VideoGamesEndPoints.ALL_VIDEO_GAMES)
                .then().log().all();

    }

}
