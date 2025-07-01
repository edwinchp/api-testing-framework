package wiremock.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import wiremock.server.WireMockServerManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class SuccessStubs {

    WireMockServer wireMock = WireMockServerManager.getInstance().getWireMock();

    public void stubAccepted(){
        wireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/wm/v1/accepted/"))
                .willReturn(aResponse()
                        .withStatus(202)
                        .withHeader("Content-Type", "application-json")
                ));
    }


}
