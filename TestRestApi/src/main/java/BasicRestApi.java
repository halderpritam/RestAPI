import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;


class BasicRestApi {

    public static void main(String args[]){

        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                param("location","-33.8670522,151.1957362").
                param("radius","1500").
                param("type","restaurant").
                param("keyword","cruise").
                param("key","AIzaSyDxa5VWWKn3qTH14dQCr2V0iOhbnlVGEJI").
                    when().get("maps/api/place/nearbysearch/json").
                        then().assertThat().statusCode(200);


    }

}
