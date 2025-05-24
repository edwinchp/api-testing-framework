package hooks;

import api.services.WireMockApiService;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class WireMockServerHook {

    private WireMockApiService wireMockServer;

    @BeforeAll
    public static void beforeAll() {
        WireMockApiService.getInstance().startWireMockServer();
    }

    @BeforeEach
    public void restartWireMockServer() {
        wireMockServer = WireMockApiService.getInstance();
        wireMockServer.resetStubs();
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Teardown MockServer");
        WireMockApiService.tearDownWireMockServer();
    }
}
