package steps;

import api.services.GoRestApiService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StatusCodesSteps {

    GoRestApiService goRestService = new GoRestApiService();

    @When("I fetch a valid user id the response should be {int}")
    public void iFetchAValidUserIdTheResponseShouldBe(int statusCode) {
        String firstUserId = goRestService.getActiveUsers().then().extract().jsonPath().getString("id[0]");
        goRestService.getUser(firstUserId).then().log().all().statusCode(statusCode);
    }
}
