import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestResponse {

    @Test
    public void restResponseGrab() {

        String postBody = "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
        RestAssured.baseURI = "http://216.10.245.166/";

        // Capture a particular object value from response json
        String response = given().
                queryParam("key", "qaclick123").
                body(postBody).log().all().
                when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).
                extract().asString();

        JsonPath jsonPath = JsonPath.from(response);
        String placeId = jsonPath.getString("place_id");

        // Use captured value to make delete rest api call
        String deletePostBody = "{\n" +
                "    \"place_id\":\"" + placeId + "\"\n" +
                "}";
        given().queryParams("key", "qaclick123").
                body(deletePostBody).log().ifValidationFails().
                when().post("maps/api/place/delete/json").
                then().assertThat().contentType(ContentType.JSON).and().statusCode(200).
                and().body("status", equalTo("OK"));
        System.out.println("Place Id which got delted is :- " + placeId);

    }
}
