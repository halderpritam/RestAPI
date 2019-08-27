import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


class BasicRestApi {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("type", "restaurant").
                param("keyword", "cruise").
                param("key", "AIzaSyDxa5VWWKn3qTH14dQCr2V0iOhbnlVGEJI").
                when().get("maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Cruise Bar, Restaurant & Events"));

    }

}
