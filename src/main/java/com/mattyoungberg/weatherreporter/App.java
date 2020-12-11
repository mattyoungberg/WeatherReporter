package com.mattyoungberg.weatherreporter;

import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class App {

    public static void main(String[] args) throws IOException, BadHttpRequestException {

        // Load config
        Config config = new Config();

        // Figure out where the machine is requesting from and get corresponding weather info
        Coordinates coordinates = new Coordinates(config);
        WeatherInfo weatherInfo = new WeatherInfo(coordinates.getLatitude(), coordinates.getLongitude(), config);


        // Date formatter for sunrise and sunset
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mma");

        // Print weather data to console
        System.out.println("Here is the current weather in " + weatherInfo.location + ":\n");
        System.out.println("Weather: " + weatherInfo.description);
        System.out.println("Temperature: " + weatherInfo.temperature);
        System.out.println("Feels Like: " + weatherInfo.feelsLike);
        System.out.println("High: " + weatherInfo.high);
        System.out.println("Low: " + weatherInfo.low);
        System.out.println("Windspeed: " + weatherInfo.windSpeed);
        System.out.println("Sunrise: " + weatherInfo.sunrise.format(dtf));
        System.out.println("Sunset: " + weatherInfo.sunset.format(dtf));
    }
}
