package api.services;

import api.ApiService;
import io.restassured.response.Response;
import models.gorest.GoRestUser;

public class GoRestApiService extends ApiService {

    private final String GOREST_API_AUTH_TOKEN = System.getenv("GOREST_API_AUTH_TOKEN");

    @Override
    public String getServiceName() {
        return "GoRest";
    }

    @Override
    protected void addAuthorization() {
        request.header("Authorization", "Bearer " + GOREST_API_AUTH_TOKEN);
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
        addAuthorization();
        return post("/public/v2/users", goRestUser);
    }

    public Response createUser(String jsonObject){
        addAuthorization();
        return post("/public/v2/users```", jsonObject);
    }

    public Response deleteUser(String id){
        addAuthorization();
        return delete("/public/v2/users/"+id);
    }
}
