package com.mattyoungberg.weatherreporter;

public class Config {

    private final String ipStackKey;
    private final String openWeatherKey;
    private final Unit unit;

    public Config(String ipStackKey, String openWeatherKey, Unit unit) {
        this.ipStackKey = ipStackKey;
        this.openWeatherKey = openWeatherKey;
        this.unit = unit;
    }

    public String getIpStackKey() {
        return ipStackKey;
    }

    public String getOpenWeatherKey() {
        return openWeatherKey;
    }

    public Unit getUnit() {
        return unit;
    }
}
