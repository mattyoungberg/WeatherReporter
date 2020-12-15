package com.mattyoungberg.weatherreporter;

import java.net.http.HttpClient;


public class App {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {

        // Load config
        Config config = new Config();

        // Figure out where the machine is requesting from and get corresponding weather info
        Coordinates coordinates = CoordinatesRetriever.getLocalCoordinates(config, HTTP_CLIENT);
        WeatherInfo weatherInfo = WeatherInfoRetriever.getWeatherInfo(coordinates, config, HTTP_CLIENT);

        // Print weather data to console
        System.out.println("Here is the current weather in " + weatherInfo.getLocation() + ":\n");
        System.out.println("Weather: " + weatherInfo.getDescription());
        System.out.println("Temperature: " + weatherInfo.getTemperature());
        System.out.println("Feels Like: " + weatherInfo.getFeelsLike());
        System.out.println("High: " + weatherInfo.getHigh());
        System.out.println("Low: " + weatherInfo.getLow());
        System.out.println("Windspeed: " + weatherInfo.getWindSpeed());
        System.out.println("Sunrise: " + weatherInfo.getSunriseString());
        System.out.println("Sunset: " + weatherInfo.getSunsetString());
    }
}
