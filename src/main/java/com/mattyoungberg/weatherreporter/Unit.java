package com.mattyoungberg.weatherreporter;

public enum Unit {
    STANDARD("m/s", "\u00B0K"),
    METRIC("m/s", "\u00B0C"),
    IMPERIAL("mph", "\u00B0F");

    public final String speedUnits;
    public final String tempSuffix;

    Unit(String speedUnits, String tempSuffix) {
        this.speedUnits = speedUnits;
        this.tempSuffix = tempSuffix;
    }

    public String getSpeedUnits() {
        return speedUnits;
    }

    public String getTempSuffix() {
        return tempSuffix;
    }
}
