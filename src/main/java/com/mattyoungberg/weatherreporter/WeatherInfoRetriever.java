package com.mattyoungberg.weatherreporter;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherInfoRetriever {


    public static WeatherInfo getWeatherInfo(Coordinates coordinates, Config config, HttpClient httpClient) throws Exception {
        HttpResponse<String> response = callOpenWeather(httpClient, config.getOpenWeatherKey(), String.valueOf(coordinates.getLatitude()),
                String.valueOf(coordinates.getLongitude()), config.getUnit());
        return parseResponse(response.body(), config.getUnit());
    }

    private static HttpResponse<String> callOpenWeather(HttpClient httpClient, String apiKey, String latitude, String longitude, Unit unit) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude +
                        "&appid=" + apiKey + "&units=" + unit.name()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponseChecker.throwIfNot2xx(response);
        return response;
    }

    private static WeatherInfo parseResponse(String response, Unit unit) {  // Is Map<String, Object> too broad?
        JSONObject weatherJson = new JSONObject(response);

        String location = weatherJson.getString("name");
        String description = weatherJson.getJSONArray("weather").getJSONObject(0).getString("main");
        float temperature = weatherJson.getJSONObject("main").getFloat("temp");
        float feelsLike = weatherJson.getJSONObject("main").getFloat("feels_like");
        float high = weatherJson.getJSONObject("main").getFloat("temp_max");
        float low = weatherJson.getJSONObject("main").getFloat("temp_min");
        float windSpeed = weatherJson.getJSONObject("wind").getFloat("speed");
        long sunrise = weatherJson.getJSONObject("sys").getLong("sunrise");
        long sunset = weatherJson.getJSONObject("sys").getLong("sunset");
        long timeOffset = weatherJson.getLong("timezone");

        return new WeatherInfo(unit, location, description, temperature, feelsLike, high, low, windSpeed, sunrise, sunset, timeOffset);
    }
}
