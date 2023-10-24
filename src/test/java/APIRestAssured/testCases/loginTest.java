package APIRestAssured.testCases;

import APIRestAssured.BaseForEachRequest.baseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class loginTest extends baseClass {


    @Test
    public  void TC1_Verify_Login(){

        token = RestAssured.given()
                .spec(request)
                .body(validLogin)
       .when()
               .post("/cp_internal/login")
       .then()

                .log().all()
                .assertThat()
                // Validate the status code of the  API response
                .statusCode(200)
               .extract()
               .jsonPath()
                // Extract the token from the response
               .getString("token");
        // Assert that the token is not null or empty
                Assert.assertNotEquals(null,token);

    }

    @Test

    public void TC2_Verify_Invalid_Login(){

         given()
                .spec(request)
                .body(invalidLogin)
                .when()
                .post("/cp_internal/login")
                .then()
                .log().all()
                .assertThat()
                // Validate the status code of the  API response
                .statusCode(500);
    }


    @Test
    public void TC3_Verify_Whoami_Detailes_ResponseCode(){

        RestAssured.given()
                .spec(request)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/cp_internal/whoami")
                .then()
                .log().all()
                // Validate the status code of the second API response
                .assertThat().statusCode(200)
                .assertThat().body("user.merchant.id",equalTo("6d0423e8-72b9-4d26-a0a3-c835487381a5"));
    }


}
