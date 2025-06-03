package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class ApiService {
    protected RequestSpecification request;

    public ApiService(String baseUri) {
        request = RestAssured.given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log()
                .all();
    }

    public abstract String getServiceName();

    protected abstract void addAuthorization();


    protected Response get(String endpoint) {
        return request.when().get(endpoint);
    }

    protected Response post(String endpoint, Object body) {
        return request.body(body).when().post(endpoint);
    }

    protected Response post(String endpoint) {
        return request.when().post(endpoint);
    }

    protected Response put(String endpoint, Object body) {
        return request.body(body).when().put(endpoint);
    }

    protected Response delete(String endpoint) {
        return request.when().delete(endpoint);
    }

}
