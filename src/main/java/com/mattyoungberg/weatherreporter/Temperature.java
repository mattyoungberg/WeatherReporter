package com.mattyoungberg.weatherreporter;

public class Temperature {

    private final float temperature;
    private final String suffix;

    public Temperature(float temperature, Unit unit) {
        this.temperature = temperature;
        this.suffix = unit.getTempSuffix();
    }

    @Override
    public String toString() {
        return Math.round(temperature) + suffix;
    }
}
