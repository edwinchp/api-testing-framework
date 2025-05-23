package api.services;

import api.ApiService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
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
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);

        stubFor(WireMock.post(urlEqualTo("/async-job"))
                .willReturn(aResponse().withStatus(202))
        );

    }

    public static void tearDownWireMockServer() {
        wireMockServer.stop();
    }
}
