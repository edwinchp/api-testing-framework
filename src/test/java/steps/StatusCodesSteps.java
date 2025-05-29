package steps;

import api.services.GoRestApiService;
import api.services.WireMockApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.When;
import models.gorest.GoRestUser;

import java.util.Random;

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

    @When("I create a new user the response should be {int} no content")
    public void iCreateANewUserTheResponseShouldBeNoContent(int statusCode) {
        String firstUserId = goRestService.getActiveUsers().then().extract().jsonPath().getString("id[0]");
        goRestService.deleteUser(firstUserId).then().statusCode(statusCode);
    }

    @When("I send an incorrect request the response should be {int} bad request")
    public void iSendAnIncorrectRequestTheResponseShouldBeBadRequest(int statusCode) {
        Random rand = new Random();
        ObjectMapper mapper = new ObjectMapper();
        String randomEmail = "ramdomtestuser" + (rand.nextInt(1000) + 1) + "@gmail.com";
        String brokenJson;
        GoRestUser goRestUser = GoRestUser.builder()
                .name("Edwin")
                .email(randomEmail)
                .gender("Male")
                .status("active")
                .build();

        try {
            String validJson = mapper.writeValueAsString(goRestUser);
            brokenJson = validJson.substring(0, validJson.length() -1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        goRestService.createUser(brokenJson).then().statusCode(statusCode);
    }
}