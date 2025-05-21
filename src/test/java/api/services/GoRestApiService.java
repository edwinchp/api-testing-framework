package api.services;

import api.ApiService;
import io.restassured.response.Response;

import java.util.Random;

public class GoRestApiService extends ApiService {

    @Override
    public String getServiceName() {
        return "GoRest";
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

    public Response createUser(){
        addAuthToken(System.getenv("GOREST_API_AUTH_TOKEN"));
        Random rand = new Random();
        String randomEmail = "ramdomtestuser" + String.valueOf(rand.nextInt(1000) + 1) + "@gmail.com";
        String body = "{\n" +
                "  \"name\": \"Test User\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"" + randomEmail + "\",\n" +
                "  \"status\": \"active\"\n" +
                "}";
        return post("/public/v2/users", body);
    }
}
