package api.services;

import api.ApiService;
import io.restassured.response.Response;
import models.gorest.GoRestUser;

import java.util.Random;

public class GoRestApiService extends ApiService {

    private final String GOREST_API_AUTH_TOKEN = System.getenv("GOREST_API_AUTH_TOKEN");

    @Override
    public String getServiceName() {
        return "GoRest";
    }

    @Override
    protected void addAuthToken(String token) {
        request.header("Authorization", "Bearer " + token);
    }

    public GoRestApiService() {
        super("https://gorest.co.in");
    }

    public Response getActiveUsers(){
        return get("/public/v2/users?status=active");
    }

    public Response getUser(String id){
        return get("/public/v2/users/"+id);
    }

    public Response createUser(GoRestUser goRestUser){
        addAuthToken(GOREST_API_AUTH_TOKEN);
        return post("/public/v2/users", goRestUser);
    }

    public Response createUser(String jsonObject){
        addAuthToken(GOREST_API_AUTH_TOKEN);
        return post("/public/v2/users```", jsonObject);
    }

    public Response deleteUser(String id){
        addAuthToken(GOREST_API_AUTH_TOKEN);
        return delete("/public/v2/users/"+id);
    }
}
