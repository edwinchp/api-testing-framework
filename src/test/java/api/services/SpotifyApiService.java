package api.services;

import api.ApiService;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class SpotifyApiService extends ApiService {

    private final String SPOTIFY_CLIENT_ID = System.getenv("SPOTIFY_CLIENT_ID");
    private final String SPOTIFY_CLIENT_SECRET = System.getenv("SPOTIFY_CLIENT_SECRET");
    private final String SPOTIFY_ACCESS_TOKEN_URL = System.getenv("SPOTIFY_ACCESS_TOKEN_URL");

    @Getter
    private String accessToken;

    public SpotifyApiService() {
        super("");
        addAuthorization();
    }

    @Override
    public String getServiceName() {
        return "SpotifyApiService";
    }

    @Override
    protected void addAuthorization() {
        String base64Credentials = Base64.getEncoder()
                .encodeToString((SPOTIFY_CLIENT_ID + ":" + SPOTIFY_CLIENT_SECRET)
                        .getBytes());

        Response response =
                given()
                        .header("Authorization", "Basic " + base64Credentials)
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("grant_type", "client_credentials")
                        .when()
                        .post(SPOTIFY_ACCESS_TOKEN_URL);

        response.then().log().all();

        accessToken = response.jsonPath().getString("access_token");
    }

    public Response getNewReleases(String accessToken){
        return given()
                .baseUri("https://api.spotify.com")
                .auth()
                .oauth2(accessToken)
                .when()
                .get("/v1/browse/new-releases");
    }
}
