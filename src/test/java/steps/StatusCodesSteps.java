package steps;

import api.services.GoRestApiService;
import api.services.WireMockApiService;
import io.cucumber.java.en.When;

public class StatusCodesSteps {

    GoRestApiService goRestService = new GoRestApiService();
    WireMockApiService wireMockApiService = new WireMockApiService();

    @When("I fetch a valid user id the response should be {int}")
    public void iFetchAValidUserIdTheResponseShouldBe(int statusCode) {
        String firstUserId = goRestService.getActiveUsers().then().extract().jsonPath().getString("id[0]");
        goRestService.getUser(firstUserId).then().log().all().statusCode(statusCode);
    }

    @When("I create a new user the response should be {int}")
    public void iCreateANewUserTheResponseShouldBe(int statusCode) {
        wireMockApiService.setWireMockServer();
        wireMockApiService.mockPostResponse().then().statusCode(statusCode);
    }
}
