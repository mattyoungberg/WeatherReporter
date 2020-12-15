package com.mattyoungberg.weatherreporter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class WeatherInfo {

    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("h:mma");

    private final Unit unit;
    private final String location;
    private final String description;
    private final Temperature temperature;
    private final Temperature feelsLike;
    private final Temperature high;
    private final Temperature low;
    private final String windSpeed;
    private final LocalDateTime sunrise;
    private final LocalDateTime sunset;


    public WeatherInfo(Unit unit, String location, String description, float temperature, float feelsLike, float high, float low, float windSpeed, long sunrise, long sunset, long timeOffSet) {
        this.unit = unit;
        this.location = location;
        this.description = description;
        this.temperature = new Temperature(temperature, unit);
        this.feelsLike = new Temperature(feelsLike, unit);
        this.high = new Temperature(high, unit);
        this.low = new Temperature(low, unit);
        this.windSpeed = windSpeed + unit.getSpeedUnits();
        this.sunrise = LocalDateTime.ofEpochSecond(sunrise + timeOffSet, 0, ZoneOffset.ofHours(0));
        this.sunset = LocalDateTime.ofEpochSecond(sunset + timeOffSet, 0, ZoneOffset.ofHours(0));
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Temperature getFeelsLike() {
        return feelsLike;
    }

    public Temperature getHigh() {
        return high;
    }

    public Temperature getLow() {
        return low;
    }

    public String getWindSpeed() {
        return windSpeed;
    }


    public String getSunriseString() {
        return sunrise.format(DTF);
    }

    public String getSunsetString() {
        return sunset.format(DTF);
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
