package api.services;

import api.ApiService;

public class GoRestApiService extends ApiService {

    @Override
    public String getServiceName() {
        return "GoRest";
    }

    public GoRestApiService() {
        super("https://gorest.co.in");
    }

    public void getActiveUsers(){
        get("/public/v2/users?status=active");
    }


}
