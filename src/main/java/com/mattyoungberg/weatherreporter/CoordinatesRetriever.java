package com.mattyoungberg.weatherreporter;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoordinatesRetriever {

    public static Coordinates getLocalCoordinates(Config config, HttpClient httpClient) throws Exception {
        HttpResponse<String> response = callIPStack(httpClient, config.getIpStackKey());
        return parseJson(response.body());
    }

    private static HttpResponse<String> callIPStack(HttpClient httpClient, String apiKey) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.ipstack.com/check?access_key=" + apiKey))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponseChecker.throwIfNot2xx(response);
        return response;
    }

    private static Coordinates parseJson(String response) {
        JSONObject jsonObject = new JSONObject(response);
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");
        return new Coordinates(latitude, longitude);
    }
}
