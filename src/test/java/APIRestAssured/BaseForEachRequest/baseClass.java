package APIRestAssured.BaseForEachRequest;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import java.io.File;

import static io.restassured.RestAssured.given;

public class baseClass {

    public baseClass(){

    }

    public static  RequestSpecification request;
    public static  File validLogin= new File("src/main/resources/validlogin.json");
    public static  File invalidLogin= new File("src/main/resources/invalidlogin.json");

    public static String token;

    @BeforeClass
    public void beforeClass(){

        request = given()
                .baseUri("https://pay2.foodics.dev")
                .header("Content-Type", "application/json")
                .log().all();
    }

}
