package test.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PostCreateUser {
    private static Response response;

    @When("setting {string} created user")
    public void setting_created_user(String URL) {
        RestAssured.baseURI = URL;
    }

    @When("set body create user {string} and {string} should be {string}")
    public void set_body_create_user_and(String name, String job, String result) {
        String requestBody = "{\n" +
                "  \"name\": "+"\"" +name+"\",\n" +
                "  \"job\": "+"\""+job+"\" \n }";

         response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract().response();

        if(result.equalsIgnoreCase("success")){
            Assert.assertEquals(response.jsonPath().getString("name"), name);
            Assert.assertEquals(response.jsonPath().getString("job"), job);
        }

    }

    @Then("{int} create user")
    public void create_user(Integer statusCode) {
        Integer statusCodeResp = response.getStatusCode();
        Assert.assertEquals(statusCodeResp, statusCode);

    }

}
