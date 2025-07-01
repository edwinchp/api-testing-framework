package hooks;

import wiremock.server.WireMockServerManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class WireMockServerHook {

    @BeforeAll
    public static void beforeAll() {
        WireMockServerManager.getInstance().startWireMockServer();
    }

    @BeforeEach
    public void restartWireMockServer() {
        WireMockServerManager wireMockServer = WireMockServerManager.getInstance();
        wireMockServer.resetStubs();
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Teardown MockServer");
        WireMockServerManager.tearDownWireMockServer();
    }
}
