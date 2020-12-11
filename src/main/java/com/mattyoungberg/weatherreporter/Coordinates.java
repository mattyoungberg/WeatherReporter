package com.mattyoungberg.weatherreporter;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Coordinates {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private final double latitude;
    private final double longitude;

    public Coordinates(Config config) throws BadHttpRequestException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.ipstack.com/check?access_key=" + config.getIpStackKey()))
                .build();
        String jsonBody = null;
        try {
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() / 100 == 2) jsonBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if(jsonBody == null) throw new BadHttpRequestException("Something went wrong in the request to IPStack. Check your config file or internet connection.");
        JSONObject jsonObject = new JSONObject(jsonBody);
        this.latitude = jsonObject.getDouble("latitude");
        this.longitude = jsonObject.getDouble("longitude");
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
