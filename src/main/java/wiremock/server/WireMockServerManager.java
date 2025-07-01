package wiremock.server;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockServerManager {

    private static volatile WireMockServerManager instance;
    private static WireMockServer wireMockServer;
    private static final Object lock = new Object();
    private static boolean isServerStarted = false;

    private WireMockServerManager() {}

    public static WireMockServerManager getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new WireMockServerManager();
                }
            }
        }
        return instance;
    }

    public synchronized void startWireMockServer(){
        if(!isServerStarted){
            System.out.println("Starting WireMock Server");
            wireMockServer = new WireMockServer(8089);
            wireMockServer.start();
            WireMock.configureFor("localhost", 8089);
            isServerStarted = true;
        }
    }

    public synchronized WireMockServer getWireMock(){
        return wireMockServer;
    }

    private static synchronized void stopWireMockServer(){
        if(isServerStarted && wireMockServer != null){
            System.out.println("Stopping WireMock Server");
            wireMockServer.stop();
            isServerStarted = false;
        }
    }

    public synchronized void resetStubs(){
        if(isServerStarted && wireMockServer != null){
            WireMock.reset();
        }
    }

    //public Response mockResponseFor202Code(){
    //    WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/mock-response-202"))
    //            .willReturn(WireMock.aResponse().withStatus(202))
    //    );
    //    return post("/mock-response-202");
    //}

    public static void tearDownWireMockServer() {
        stopWireMockServer();
    }
}
