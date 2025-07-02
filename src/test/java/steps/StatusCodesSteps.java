package steps;

import api.services.GoRestApiService;
import api.services.SpotifyApiService;
import api.services.WireMockApiService;
import wiremock.server.WireMockServerManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.When;
import models.gorest.GoRestUser;
import wiremock.stubs.ServerErrorStubs;
import wiremock.stubs.SuccessStubs;

import java.util.Random;
import java.util.UUID;

public class StatusCodesSteps {
    GoRestApiService goRestService = new GoRestApiService();
    SpotifyApiService spotifyApiService = new SpotifyApiService();
    WireMockApiService wireMockApiService = new WireMockApiService();

    @When("I fetch a valid user id the response should be {int} ok")
    public void iFetchAValidUserIdTheResponseShouldBeOk(int statusCode) {
        String firstUserId = goRestService.getActiveUsers().then().extract().jsonPath().getString("id[0]");
        goRestService.getUser(firstUserId).then().log().all().statusCode(statusCode);
    }

    @When("I create a new user the response should be {int} created")
    public void iCreateANewUserTheResponseShouldBeCreated(int statusCode) {
        String randomEmail = "ramdomtestuser" + (new Random().nextInt(1000) + 1) + "@gmail.com";
        GoRestUser goRestUser = GoRestUser.builder()
                .name("Edwin")
                .email(randomEmail)
                .gender("Male")
                .status("active")
                .build();
        goRestService.createUser(goRestUser).then().statusCode(statusCode);
    }

    @When("I create a new user the response should be {int} accepted")
    public void iCreateANewUserTheResponseShouldBeAccepted(int statusCode) {
        SuccessStubs successStubs = new SuccessStubs();
        successStubs.stubAccepted();

        wireMockApiService.postAccepted().then().statusCode(statusCode);
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

    @When("I send an incorrect Bearer token the response should return {int} unauthorized")
    public void iSendAnIncorrectBearerTokenTheResponseShouldReturnUnauthorized(int statusCode) {
        String fakeToken = UUID.randomUUID().toString();
        spotifyApiService.getNewReleases(fakeToken).then().log().body().statusCode(statusCode);
    }

    @When("I send request the response should return {int} forbidden")
    public void iSendRequestTheResponseShouldReturnForbidden(int statusCode) {
        String playListId = "3cEYpjA9oz9GiPac4AsH4n";
        spotifyApiService.updatePlaylist(playListId)
                .then()
                .statusCode(statusCode);
    }

    @When("I send request the response should return {int} not found")
    public void iSendRequestTheResponseShouldReturnNotFound(int statusCode) {
        String playListId = "3cEYpjA9xz9GiPac4AsH4n";
        spotifyApiService.getPlaylist(playListId)
                .then()
                .log().all()
                .statusCode(statusCode);
    }

    @When("I fetch data from an endpoint the response should be {int} internal server error")
    public void iFetchDataFromAnEndpointTheResponseShouldBeInternalServerError(int statusCode) {
        ServerErrorStubs serverErrorStubs = new ServerErrorStubs();
        serverErrorStubs.stubInternalServerError();

        wireMockApiService.getInternalServerError().then().statusCode(statusCode);
    }

    @When("I fetch data from an endpoint the response should be {int} bad gateway")
    public void iFetchDataFromAnEndpointTheResponseShouldBeBadGateway(int statusCode) {
        ServerErrorStubs serverErrorStubs = new ServerErrorStubs();
        serverErrorStubs.stubBadGatewayError();

        wireMockApiService.getBadGatewayError().then().statusCode(statusCode);
    }

    @When("I fetch data from an endpoint the response should be {int} service unavailable")
    public void iFetchDataFromAnEndpointTheResponseShouldBeServiceUnavailable(int statusCode) {
        ServerErrorStubs serverErrorStubs = new ServerErrorStubs();
        serverErrorStubs.stubServiceUnavailable();

        wireMockApiService.getServiceUnavailableError().then().statusCode(statusCode);
    }

    @When("I fetch data from an endpoint the response should be {int} http method not supported")
    public void iFetchDataFromAnEndpointTheResponseShouldBeHttpMethodNotSupported(int statusCode) {
        ServerErrorStubs serverErrorStubs = new ServerErrorStubs();
        serverErrorStubs.stubHttpVersionNotSupported();

        wireMockApiService.getHttpVersionNotSupportedError().then().statusCode(statusCode);
    }
}