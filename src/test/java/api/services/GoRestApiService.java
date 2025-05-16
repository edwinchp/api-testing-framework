package api.services;

import api.ApiService;
import io.restassured.response.Response;

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
}
