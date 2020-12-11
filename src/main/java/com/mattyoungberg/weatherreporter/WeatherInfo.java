package com.mattyoungberg.weatherreporter;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WeatherInfo {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public final String description;
    public final Temperature temperature;
    public final Temperature feelsLike;
    public final Temperature high;
    public final Temperature low;
    public final String windSpeed;
    public final LocalDateTime sunrise;
    public final LocalDateTime sunset;
    public final String location;

    public WeatherInfo(double latitude, double longitude, Config config) throws BadHttpRequestException {
        // Set units
        Unit unit = config.getUnit();

        // Get weather data from OpenWeatherMap
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude +
                        "&appid=" + config.getOpenWeatherKey() + "&units=" + unit.name()))
                .build();
        String jsonBody = null;
        try {
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() / 100 == 2) jsonBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if(jsonBody == null) throw new BadHttpRequestException("Something went wrong in the request to OpenWeather. Check your config file or internet connection.");
        JSONObject weatherJson = new JSONObject(jsonBody);

        // Setting fields

        // Description
        this.description = weatherJson.getJSONArray("weather").getJSONObject(0).getString("main");
        // Temperatures
        this.temperature = new Temperature(weatherJson.getJSONObject("main").getFloat("temp"), unit);
        this.feelsLike = new Temperature(weatherJson.getJSONObject("main").getFloat("feels_like"), unit);
        this.high = new Temperature(weatherJson.getJSONObject("main").getFloat("temp_max"), unit);
        this.low = new Temperature(weatherJson.getJSONObject("main").getFloat("temp_min"), unit);
        // Windspeed
        this.windSpeed = weatherJson.getJSONObject("wind").getFloat("speed") + unit.getSpeedUnits();
        // Sunrise & Sunset
        long timeOffset = weatherJson.getLong("timezone");  // Seconds offsetting sunrise/sunset from UTC to local tz
        long unixSunrise = weatherJson.getJSONObject("sys").getLong("sunrise");
        long unixSunset = weatherJson.getJSONObject("sys").getLong("sunset");
        this.sunrise = LocalDateTime.ofEpochSecond(unixSunrise + timeOffset, 0, ZoneOffset.ofHours(0));
        this.sunset = LocalDateTime.ofEpochSecond(unixSunset + timeOffset, 0, ZoneOffset.ofHours(0));
        // Location
        this.location = weatherJson.getString("name");
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "description='" + description + '\'' +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", high=" + high +
                ", low=" + low +
                ", windSpeed=" + windSpeed +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", location='" + location + '\'' +
                '}';
    }
}
