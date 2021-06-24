package test.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class GetData {
    private static Response response;

    @When("set {string} list user")
    public void set_list_user(String URL) {
        RestAssured.baseURI = URL;

    }

    @When("set {string} get response API a list of users")
    public void set_get_response_API_a_list_of_users(String method) {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, method);
        String responseBody = response.getBody().asString();
        //System.out.println("responseBody API: "+responseBody);

    }

    @Then("{int} get list user")
    public void get_list_user(Integer statusCode) {
        Integer statusCodeResp = response.getStatusCode();
        Assert.assertEquals(statusCodeResp, statusCode);

    }

    @Then("response get API {int} accessed, data {int}, {int} all user, {int} should be {string}")
    public void response_get_API_accessed_data_all_user(Integer page, Integer per_page, Integer total, Integer total_pages, String result) {

        if(result.equalsIgnoreCase("success")){
            Integer pageResp = response.jsonPath().getInt("page");
            Integer per_pageResp = response.jsonPath().getInt("per_page");
            Integer totalResp = response.jsonPath().getInt("total");
            Integer total_pagesResp = response.jsonPath().getInt("total_pages");

            Assert.assertEquals(pageResp, page);
            Assert.assertEquals(per_pageResp, per_page);
            Assert.assertEquals(totalResp, total);
            Assert.assertEquals(total_pagesResp, total_pages);
        }

    }
}
