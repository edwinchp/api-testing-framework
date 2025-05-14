package steps;

import api.ApiService;
import api.ApiServiceFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StatusCodesSteps {

    ApiService goRestService = ApiServiceFactory.create("gorest");

    @When("I fetch user with id {int}")
    public void iFetchUserWithId(int arg0) {
        Assert.assertEquals("GoRest", goRestService.getServiceName());
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int arg0) {

    }
}
