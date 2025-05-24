package steps;

import api.services.GoRestApiService;
import api.services.WireMockApiService;
import io.cucumber.java.en.When;

public class StatusCodesSteps {

    GoRestApiService goRestService = new GoRestApiService();
    WireMockApiService wireMockApiService = WireMockApiService.getInstance();

    @When("I fetch a valid user id the response should be {int} ok")
    public void iFetchAValidUserIdTheResponseShouldBeOk(int statusCode) {
        String firstUserId = goRestService.getActiveUsers().then().extract().jsonPath().getString("id[0]");
        goRestService.getUser(firstUserId).then().log().all().statusCode(statusCode);
    }

    @When("I create a new user the response should be {int} created")
    public void iCreateANewUserTheResponseShouldBeCreated(int statusCode) {
        wireMockApiService.mockPostResponse().then().statusCode(statusCode);
    }

    @When("I create a new user the response should be {int} accepted")
    public void iCreateANewUserTheResponseShouldBeAccepted(int statusCode) {
        wireMockApiService.mockResponseFor202Code().then().statusCode(statusCode);
    }
}