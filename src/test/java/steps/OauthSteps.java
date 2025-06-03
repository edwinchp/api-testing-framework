package steps;

import api.services.SpotifyApiService;
import io.cucumber.java.en.When;

public class OauthSteps {

    SpotifyApiService spotifyApiService = new SpotifyApiService();

    @When("I enter Google credentials the Spotify API allows login access")
    public void iEnterGoogleCredentialsTheSpotifyAPIAllowsLoginAccess() {
            spotifyApiService.checkAboutMe().then().log().body();
    }
}
