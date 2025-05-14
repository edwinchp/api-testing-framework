package api;

import api.services.GoRestApiService;

public class ApiServiceFactory {
    public static ApiService create(String serviceName) {
        return switch (serviceName.toLowerCase()) {
            case "gorest" -> new GoRestApiService();
            case "trello" -> null;
            default -> throw new IllegalArgumentException("Unknown service name: " + serviceName);
        };
    }
}