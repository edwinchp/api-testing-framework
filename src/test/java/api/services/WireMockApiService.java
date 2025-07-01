package api.services;

import api.ApiService;
import io.restassured.response.Response;

public class WireMockApiService extends ApiService {

    public WireMockApiService() {
        super("http://localhost:8089");
    }

    @Override
    public String getServiceName() {
        return "http://localhost:8089";
    }

    @Override
    protected void addAuthorization() {

    }

    public Response postAccepted(){
        return post("/wm/v1/accepted/");
    }

    public Response getInternalServerError(){
        return get("/wm/v1/internal-server-error/");
    }
}
