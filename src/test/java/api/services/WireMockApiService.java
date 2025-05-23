package api.services;

import api.ApiService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockApiService extends ApiService {

    private static WireMockServer wireMockServer;

    public WireMockApiService() {
        super("http://localhost:8089");
    }

    @Override
    public String getServiceName() {
        return "WireMockApiService";
    }

    public void setWireMockServer(){
        System.out.println("Starting WireMock Server");
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);

        stubFor(WireMock.post(urlEqualTo("/async-job"))
                .willReturn(aResponse().withStatus(201))
        );
    }

    public Response mockPostResponse(){
       return post("/async-job");
    }

    public static void tearDownWireMockServer() {
        wireMockServer.stop();
    }
}
