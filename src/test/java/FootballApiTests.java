import config.FootballApiConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FootballApiTests extends FootballApiConfig {

    @Test
    public void getDetailsOfOneArea() {
        given()
                .queryParam("areas",2072).
        when()
                .get("areas");
    }

    @Test
    public void getDateFounded() {
        given().
        when()
                .get("teams/57").
        then()
                .body("founded",equalTo(1886));
    }

    @Test
    public void getFirstTeamName() {
        given().
        when()
                .get("competitions/2021/teams").
        then()
                .body("teams.name[0]",equalTo("Arsenal FC"));
    }
}
