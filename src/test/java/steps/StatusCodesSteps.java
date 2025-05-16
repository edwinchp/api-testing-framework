package steps;

import api.services.GoRestApiService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StatusCodesSteps {

    GoRestApiService goRestService = new GoRestApiService();


    @When("I fetch user with id {string} the response should be {int}")
    public void iFetchUserWithId(String id, int statusCode) {
        goRestService.getUser(id).then().log().all().statusCode(statusCode);
    }
}
