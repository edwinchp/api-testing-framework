package wiremock.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import wiremock.server.WireMockServerManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class ServerErrorStubs {
    WireMockServer wireMock = WireMockServerManager.getInstance().getWireMock();

    public void stubInternalServerError(){
        wireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/wm/v1/internal-server-error/"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application-json")
                ));
    }

    public void stubBadGatewayError(){
        wireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/wm/v1/bad-gateway-error/"))
                .willReturn(aResponse()
                        .withStatus(502)
                        .withHeader("Content-Type", "application-json")
                ));
    }

    public void stubServiceUnavailable(){
        wireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/wm/v1/service-unavailable/"))
                .willReturn(aResponse()
                        .withStatus(503)
                        .withHeader("Content-Type", "application-json")
                ));
    }

    public void stubHttpVersionNotSupported(){
        wireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/wm/v1/http-version-not-supported/"))
                .willReturn(aResponse()
                        .withStatus(505)
                        .withHeader("Content-Type", "application-json")
                ));
    }
}