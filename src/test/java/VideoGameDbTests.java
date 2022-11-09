import config.VideoGameConfig;
import config.VideoGamesEndPoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGameDbTests extends VideoGameConfig {

    @Test
    public void getAllGames() {
        given().
        when().get(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then();
    }
}
