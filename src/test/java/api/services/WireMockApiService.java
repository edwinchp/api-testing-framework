package api.services;

import api.ApiService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockApiService extends ApiService {

    private static volatile WireMockApiService instance;
    private static WireMockServer wireMockServer;
    private static final Object lock = new Object();
    private boolean isServerStarted = false;

    private WireMockApiService() {
        super("http://localhost:8089");
    }

    @Override
    public String getServiceName() {
        return "WireMockApiService";
    }

    @Override
    protected void addAuthorization() { }

    public static WireMockApiService getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new WireMockApiService();
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

    public synchronized void stopWireMockServer(){
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

    public Response mockPostResponse(){
        stubFor(WireMock.post(urlEqualTo("/async-job"))
                .willReturn(aResponse().withStatus(201))
        );
        return post("/async-job");
    }

    public Response mockResponseFor202Code(){
        stubFor(WireMock.post(urlEqualTo("/mock-response-202"))
                .willReturn(aResponse().withStatus(202))
        );
        return post("/mock-response-202");
    }

    public static void tearDownWireMockServer() {
        wireMockServer.stop();
    }
}
