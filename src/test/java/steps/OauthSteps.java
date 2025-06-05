package steps;

import api.services.SpotifyApiService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class OauthSteps {

    SpotifyApiService spotifyApiService = new SpotifyApiService();

    @When("I successfully authenticate then validate the new album releases have the following properties:")
    public void iSuccessfullyAuthenticateValidateTheNewAlbumReleasesHaveTheFollowingInformation(DataTable properties) {
        List<String> expectedProperties = properties.column(0);
        List<Map<String, Object>> albumNames = spotifyApiService
                .getNewReleases(spotifyApiService.getAccessToken())
                .jsonPath()
                .getList("albums.items");

        for (Map<String, Object> album : albumNames) {
            for (String property : expectedProperties) {
                Assert.assertTrue("Album " + album.get("name") + " missing the property: " + property, album.containsKey(property));
            }
        }
    }
}
